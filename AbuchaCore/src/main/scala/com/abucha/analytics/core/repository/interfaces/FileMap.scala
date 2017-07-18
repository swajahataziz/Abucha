package com.abucha.analytics.core.repository.interfaces

/**
  * Created by Syed.Aziz on 11/04/2017.
  */
trait FileMap {
  var count: Int
  var files: Map[File,Int]

  def contains(file: File): Boolean
  def contains(fileName: String): Boolean
  def indexOf(file: File): Int
  def indexOf(fileName: String): Int
}
