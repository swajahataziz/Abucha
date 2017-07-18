package com.abucha.analytics.common.collection

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Syed.Aziz on 09/04/2017.
  */
class BinaryPriorityQueue[T <: Comparable[T]] extends mutable.PriorityQueue[T] {

  val heap:ArrayBuffer[T]
  private val value2indexMap = new mutable.HashMap[T,Int]()

  override def size: Int = heap.size
  override def isEmpty: Boolean = size == 0
  override def toString: String = size + ": " + heap.toString() + "\n" + value2indexMap.toString()
  def contains(value: T): Boolean = value2indexMap.contains(value)
  def get(value:T): Int = if (contains(value)) value2indexMap(value) else -1
  def root: T = heap(0)
  def ++(value: T) = push(value)
  override def +=(value: T): Unit = push(value)

  def push(value: T): Unit = {
    if (value2indexMap.contains(value))
      update(value)
    else {
      heap += value
      value2indexMap += (value -> (size-1))
      bubbleUp(size -1)
    }
  }

  /*Update Priority*/
  def update(value: T): Unit = {
    try {
      val index = value2indexMap(value)
      val oldValue = heap(index)
      if (value != oldValue) {
        heap.update(index, value)
        if (index > 0 && value.compareTo(heap((index-1)/2)) == -1)
          bubbleUp(index)
        else
          bubbleDown(index)
      }
    } catch {
      case e: NoSuchElementException => println("Value does not exist in Priority Queue")
    }
  }

  def pop: T = {
    if (size == 0) throw new Exception("BinaryPriorityQueue: Heap is Empty")
    val min = root
    if (size == 1) {
      heap.remove(0)
    } else if (size > 1) {
      val last = heap.remove(size -1)
      value2indexMap += (last -> 0)
      value2indexMap -= min
      heap.update(0, last)
      bubbleDown(0)
    }
    min
  }

  def -=(value:T) = delete(value)
  def --(value: T) = delete(value)
  def delete(value:T): Unit = {
    try {
      val index = value2indexMap(value)
      value2indexMap -= value
      val val2Delete = heap(index)
      val last = heap.remove(size-1)
      heap.update(index, last)
      value2indexMap += (last -> index)
      if(index > 0 && last.compareTo(heap((index - 1)/2)) == -1)
        bubbleUp(index)
      else bubbleDown(index)
    } catch {
      case e: NoSuchElementException => println("BinaryPriorityQueue: No such key exists to be deleted")
    }
  }

  private def bubbleUp(index: Int): Unit= {
    val (child,parent) = (heap(index), heap((index-1)/2))
    if (index != 0 && child.compareTo(parent) == -1) {
      heap.update((index-1)/2, child)
      heap.update(index, parent)
      value2indexMap += (child -> (index-1)/2, parent -> index)
      bubbleUp((index-1) / 2)
    }
  }

  private def bubbleDown(index: Int) {
    val leftChildIndex = 2 * index + 1
    if (leftChildIndex >= size) return
    val rightChildIndex = leftChildIndex + 1
    val leftChild = heap(leftChildIndex)
    val minIndex = {
      if (rightChildIndex < size)
        if (heap(rightChildIndex).compareTo(leftChild) == -1)
          rightChildIndex
        else leftChildIndex
      else leftChildIndex
    }
    val min = heap(minIndex)
    if (min.compareTo(heap(index)) == -1) {
      val currentValue = heap(index)
      heap.update(minIndex, currentValue)
      heap.update(index, min)
      value2indexMap += (currentValue -> minIndex, min -> index)
    }
    bubbleDown(minIndex)
  }
}
