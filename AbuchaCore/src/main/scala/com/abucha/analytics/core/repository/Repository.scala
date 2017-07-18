package com.abucha.analytics.core.repository

import com.abucha.analytics.core.repository.common.{ConnectionStringPropertyMap, MapEntry}
import com.abucha.analytics.core.repository.exception.RepositoryConnectionException
import com.abucha.analytics.core.repository.interfaces.{FileMap, FolderMap}

import scala.collection.mutable

/**
  * Created by Syed.Aziz on 23/04/2017.
  */
class Repository extends interfaces.Repository {

  val files: FileMap
  val filesIndexedByPropertyValues: mutable.HashTable[File, Int]
  val folders: FolderMap
  var isConnected: Boolean
  var path: String
  val repositoryName: String = "LocalRepository"

  def connect(connectionString: String): Unit = {
    var path: String = null
    val connectionStringPropertyMap = new ConnectionStringPropertyMap(connectionString)

    for ((k,v) <- connectionStringPropertyMap) {
      if ("path" == k) {
        path = v
      }
    }
    if (path == null) {
      throw new RepositoryConnectionException("Connection failed.  Missing required connection string property '" + path + "'."))
    }
    val dir = new java.io.File(path)
    if (!dir.exists()) {
      throw new RepositoryConnectionException("Connection failed.  Repository config location '" + path + "' could not be found.")
    }
    this.path = path
    this.isConnected = true
  }

  def loadManifestInternal(fileName: String): Unit = {

  }

  def unindexFileForProperty(file: File, property: MapEntry): Unit = {

  }
  def indexFileForProperty(file: File, property: MapEntry): Unit = {
  }



}
