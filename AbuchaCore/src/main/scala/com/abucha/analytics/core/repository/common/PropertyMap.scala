package com.abucha.analytics.core.repository.common

import com.abucha.analytics.core.repository.{File, Folder, Repository}

import scala.collection.mutable

/**
  * Created by Syed.Aziz on 24/04/2017.
  */
class PropertyMap {
  private var file: File = null
  private var folder: Folder = null
  private var repository: Repository = null
  var propertyMap: mutable.LinkedHashMap[String, String] = null

  def add(property: MapEntry): Unit = {
    propertyMap.put(property.name,property.value)
  }

  def addNew(propertyName: String, propertyValue: String): MapEntry = {

    val property: MapEntry = new MapEntry(propertyName, propertyValue)

    if (propertyName == null) {
      throw new IllegalArgumentException("Property Name argument for" + propertyName + "cannot be null")
    }
    if (propertyValue == null) {
      throw new IllegalArgumentException("Property Value argument for" + propertyName + "cannot be null")
    }
    if (propertyMap.contains(propertyName)) {
      if (this.folder != null) {
        throw new NotImplementedError()
      }
      this.repository.unindexFileForProperty(this.file, property)
      this.repository.indexFileForProperty(this.file, property)
    }
    if (this.folder != null) {
      this.folder.properties.add(property)
      this.repository.indexFileForProperty(file, property)
    }
    property
  }

  def clear(): Unit = {
    if (this.file != null) {
      for ((k,v) <- this.propertyMap) {
        this.repository.unindexFileForProperty(this.file,new MapEntry(k,v))
      }
    }
    propertyMap.clear()
  }

  def contains(property: MapEntry): Boolean = {
    propertyMap.contains(property.name)
  }

  def contains(propertyName: String): Boolean = {
    propertyMap.contains(propertyName)
  }

  def remove(property: MapEntry): Unit = {
    this.propertyMap.remove(property.name)
    if((this.file != null) && (property != null)) {
      this.repository.unindexFileForProperty(file,property)
    }
  }

  def remove(propertyName: String): Unit = {
    var propertyValue: Option[String] = null
    if (this.contains(propertyName)) {
      propertyValue = this.propertyMap.remove(propertyName)
    }
    val property: MapEntry = new MapEntry(propertyName,propertyValue.get)
    if((this.file != null) && (property != null)) {
      this.repository.unindexFileForProperty(file,property)
    }
  }
}
