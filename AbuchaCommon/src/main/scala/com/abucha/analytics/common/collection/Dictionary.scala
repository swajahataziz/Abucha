package com.abucha.analytics.common.collection

import java.io.InvalidObjectException
import java.util

import scala.collection.mutable

/**
  * Created by Syed.Aziz on 26/04/2017.
  */
trait Dictionary {

  var lastCacheContainsKey: AnyRef
  var lastCacheContainsValue: Boolean
  var lastCacheItemKey: AnyRef
  var lastCacheItemValue: DictionaryElement
  var caseSensitive: Boolean = true
  var hashTable: mutable.HashMap[AnyRef,DictionaryElement]
  var arrayList: util.ArrayList[DictionaryElement]
  var modified: Boolean


  def add(dictionaryElement: DictionaryElement): Unit = {
    if (dictionaryElement == null) {
      throw new IllegalArgumentException("Cannot add null Dictionary Element to Dictionary")
    }
    if (dictionaryElement.key == null) {
      throw new InvalidObjectException("Dictionary Element's key cannnot be null")
    }
    this.lastCacheContainsKey = null
    this.lastCacheItemKey = null
    this.modified = true
    var key = dictionaryElement.key
    if (!this.caseSensitive && key.isInstanceOf[String]) {
      key = key.toString.toLowerCase
    }
    if (this.hashTable.contains(key)) {
      throw new UnsupportedOperationException("Key "+ key.toString+" already exists in the dictionary")
    }
    this.arrayList.add(dictionaryElement)
    this.hashTable += (key -> dictionaryElement)
    val dictionaryEvent = new DictionaryEvent(this,CrudOp.Insert,dictionaryElement)
  }

  def add(key:String, dictionaryElement: DictionaryElement): Unit = {
    dictionaryElement.key = key
    this.add(dictionaryElement)
  }

  def clear(): Unit = {
    this.lastCacheContainsKey = null
    this.lastCacheItemKey = null
    this.modified = true
    this.arrayList.clear()
    this.hashTable.clear()
  }

  def clone(dictionary: Dictionary): Unit = {
    for (item <- arrayList) {
      dictionary.arrayList.add(item)
    }
    for (element <- dictionary.hashTable) {
      var key = element._1
      if (!this.caseSensitive && key.isInstanceOf[String]) {
        key = key.toString.toLowerCase()
      }
      dictionary.hashTable += (key -> element._2)
    }
    this.modified = true
  }

  def contains(dictionaryElement: DictionaryElement): Boolean = {
    if (dictionaryElement == null) {
      return false
    }
    if (dictionaryElement.key == null) {
      return false
    }
    if (this.caseSensitive) {
      return this.hashTable.contains(dictionaryElement.key)
    }
    val key = dictionaryElement.key.toString.toLowerCase
    this.hashTable.contains(key)
  }

  def containsKey(key: AnyRef): Boolean = {
    var key2 = key
    if(!this.caseSensitive && key2.isInstanceOf[String]) {
      key2 = key2.toString.toLowerCase()
    }
    if (key2 == null) {
      return false
    }
    if (this.lastCacheContainsKey == key2) {
      return this.lastCacheContainsValue
    }
    val containsKey = hashTable.contains(key2)
    this.lastCacheContainsKey = key2
    this.lastCacheContainsValue = containsKey
    lastCacheContainsValue
  }


}
