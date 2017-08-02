package com.abucha.analytics.core.dsl.component

import com.abucha.analytics.core.dsl.ComponentInstance
import com.abucha.analytics.core.util.DataSerialisable
import com.abucha.analytics.core.util.dto.DataTransferable

import scala.collection.BitSet

class ComponentRecord extends ComponentInstance with Comparable[ComponentRecord] with DataSerialisable with DataTransferable{



}

object ComponentRecord{
  val reportId: String = "report.id"
  val documentType: String = "document.type"
  val documentDescription: String = "document.description"
  val reportDataSource: String = "report.datasource"
  val entryPaths: String = "entry.paths"
  val queryType: String = "query.type"
  val currentQuery: String = "query.current"
  val normal: String = "normal"
  val preAggregate: String = "pre-aggregate"
  val cube: String = "cube"
  val cubeColumnType = "cube.column.type"
  val dataSourceType: String = "datasource.type"
  val dimensions: Int = 0
  val measures: Int = 1
  val dateDimensions = 2
  private val nullString = "_Null_"
  private val clazz = "class=\"com.abucha.analytics.core.dsl.component.ComponentRecord\""

  sealed abstract class ComponentType(val id: Int, val bitIndex: Int, val flag: Array[BitSet]){



  }

  case class Unknown(override val id: Int = 0, override val bitIndex: Int = 0, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Folder(override val id: Int = 1, override val bitIndex: Int = 1, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class PhysicalFolder(override val id: Int = 2, override val bitIndex: Int = 3, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Document(override val id: Int = 3, override val bitIndex: Int = 2, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Data(override val id: Int = 4, override val bitIndex: Int = 4, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Column(override val id: Int = 5, override val bitIndex: Int = 8, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Table(override val id: Int = 6, override val bitIndex: Int = 16, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Query(override val id: Int = 7, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }




}