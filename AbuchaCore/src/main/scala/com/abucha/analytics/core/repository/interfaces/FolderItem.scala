package com.abucha.analytics.core.repository.interfaces

/**
  * Created by Syed.Aziz on 10/04/2017.
  */
trait FolderItem {
  def removeFromRepository
  def removeFromRepository(keepInDisk: Boolean)

  val caption: String
  val name: String
  val folder: FolderItem
}
