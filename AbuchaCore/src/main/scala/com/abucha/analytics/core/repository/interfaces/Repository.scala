package com.abucha.analytics.core.repository.interfaces

import com.abucha.analytics.core.repository.common.{FileEventHandler, FolderEventHandler}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Syed.Aziz on 10/04/2017.
  */
trait Repository {
  var fileEventHandler: FileEventHandler
  var folderEventHandler: FolderEventHandler
  var files: FileMap
  var folders: FolderMap
  var name: String

  def connect(connectionString: String): Unit
  def disconnect: Unit
  def getFileByName(fileName: String): File
  def getFilesByPropertyName(propertyName: String): FileMap
  def getFilesByPropertyValue(propertyName: String, propertyValue: String): FileMap
  def getFilesByPropertyValues(propertyNames: ArrayBuffer[String], propertyValues: ArrayBuffer[String]): FileMap
  def getFolderByName(folderName: String): Folder
  def reload: Unit
  def save: Unit
  def isConnected: Boolean
}
