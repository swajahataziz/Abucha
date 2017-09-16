package com.abucha.analytics.core.report

import java.io.PrintWriter

import com.abucha.analytics.core.util.{Toolkit, XMLSerialisable}
import com.abucha.analytics.core.util.dto.{BaseDto, DataTransferable}

import scala.util.Sorting
import scala.util.control.Breaks

class ColumnComparator(val tableId: Option[String], mapping: Vector[Array[Array[Int]]]) extends Serializable with Cloneable with XMLSerialisable with DataTransferable {

  def getEntryCount: Int = this.mapping.size

  //TODO: Needs further review
  def addEntry(array1: Option[Array[Int]], array2: Option[Array[Int]]): Vector[AnyRef] = {

    var counter: Int = 0
    var internalMapping = this.mapping

    array1 match {
      case Some(arrayx) => array2 match  {
        case Some(arrayy) => {
          Sorting.quickSort(arrayx)
          Sorting.quickSort(arrayy)
          val array3 = this.fillGaps(arrayx)
          val array4 = this.fillGaps(arrayy)

          var columns: Array[Int] = new Array[Int](0)
          Breaks.breakable {
            for (i <- 0 to this.getEntryCount) {
              columns = this.getColumns(i)
              if (columns.length > 0 && columns(0) > array3(0)) {
                Breaks.break()
              }
              counter += 1
            }
          }

          internalMapping = this.insertAt(internalMapping,counter,Array(array3, array4))

          for (i <- 0 to this.getEntryCount) {
            if (i != counter) {
              columns = this.getColumns(i)
              val targetColumns = this.getTargetColumns(i)
              if (this.overlaps(columns,array3) || this.overlaps(targetColumns,array4) ||
                this.crossover(columns,targetColumns,array3, array4)) {
                internalMapping = this.removeAt(internalMapping, i)
                if (counter > i) {
                  counter -= 1
                }
              }
            }
          }
          internalMapping
        }
      }
    }
  }


  def getColumns(index: Int): Array[Int] = mapping(index)(0)

  def getTargetColumns(index: Int): Array[Int] = mapping(index)(1)

  def removeEntry(index: Int): Vector[Array[Array[Int]]] = this.removeAt(this.mapping,index)

  def isValid: Boolean = {
    var m: Int = -1
    var n: Int = -1

    for(i <- 0 to this.getEntryCount) {
      val columns = this.getColumns(i)
      val targetColumns = this.getTargetColumns(i)

      for(j <- 0 to columns.length) {
        if (columns(j) <= m) {
          return false
        }
        m = columns(j)
      }
      for(j <- 0 to targetColumns.length) {
        if (targetColumns(j) <= n) {
          return false
        }
        n = targetColumns(j)
      }
    }
    true
  }

  override def write(writer: PrintWriter): Unit = {
    writer.print("<columnMappings ")
    writer.print("tableId=\"" + Toolkit.escape(this.tableId) + "\'")
    writer.println(">")

    for(i <- 0 to this.getEntryCount) {
      val sourceColumns = this.getColumns(i)
      val targetColumns = this.getTargetColumns(i)
      writer.println("<entry>")
      writer.println("<columns>" + sourceColumns.toString + "</columns>")
      writer.println("<targetColumns" + targetColumns.toString + "</targetColumns>")
      writer.println("</entry>")
    }
    writer.print("</columnMappings>")
  }





  //Since Vector does not support inserting a new element at a specified position, this method was defined. In the
  //interest of keeping the object truly mutable, the method returns a new Vector
  private def insertAt[T] (vector: Vector[T], index: Int, value: T): Vector[T] = {
    val (front, back) = vector.splitAt(index)
    front ++ Vector(value) ++ back
  }

  private def removeAt[T] (vector: Vector[T], index: Int): Vector[T] = {
    val (front, back) = vector.splitAt(index)
    front ++ back.tail
  }

  private def overlaps(array1: Array[Int], array2: Array[Int]): Boolean = {
    this.overlaps(array1, array2) || this.overlaps(array2, array1)
  }

  private def overlapsCalc(array1: Array[Int], array2: Array[Int]): Boolean = {
    val len1 = array1.length - 1
    val len2 = array2.length - 1
    //second list's first member is greater than or equal to first list's first member AND
    array2(0) >= array1(0) &&
    //second list's first member is less than or equal to first list's last member
    //OR
      array2(0) <= array1(len1) ||
    //Second list's last member is greater than or equal to first list's first member AND
      array2(len2) >= array1(0) &&
    //Second list's last member is less than or equal to first list's last member
        array2(len2) <= array1(len1)
  }

  private def crossover(array1: Array[Int], array2: Array[Int], array3: Array[Int], array4: Array[Int]): Boolean = {
    array1(0) < array3(0) && array2(0) > array4(0) || array1(0) > array3(0) && array2(0) < array4(0)
  }

  private def fillGaps(array: Array[Int]): Array[Int] = {

//  check if the difference between first and last members will give length of the array
    val len: Int = array(array.length-1) - array(0) + 1
    if (array.length == len) {
      array
//  if difference doesnt match the length, we need to rebuild an ordered sequence
    } else {
      val array2: Array[Int] = Array(len)
      for (i <- 0 to array2.length) {
        array2(i) = i + array(0)
      }
      array2
    }
  }
}
