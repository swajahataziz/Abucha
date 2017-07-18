package com.abucha.analytics.core.repository.interfaces

/**
  * Created by Syed.Aziz on 23/04/2017.
  */
trait ReadOnlyFileMap {

  val count: Int
  val files: Map[File,Int]

  def contains(file: File): Boolean
  def contains(fileName: String): Boolean
  def indexOf(file: File): Int
  def indexOf(fileName: String): Int
}
