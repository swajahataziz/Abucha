package com.abucha.analytics.core.repository.interfaces

/**
  * Created by Syed.Aziz on 11/04/2017.
  */
trait FolderMap {
  var count: Int
  var folders: Map[Folder, Int]

  def addNew(caption: String): Folder
  def contains(folder: Folder): Boolean
  def contains(folderName: String): Boolean
  def indexOf(folder: Folder): Int
  def indexOf(folderName: String): Int
}
