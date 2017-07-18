package com.abucha.analytics.core.repository.common

import scala.collection.mutable

/**
  * Created by Syed.Aziz on 23/04/2017.
  */
class ConnectionStringPropertyMap(connectionString: String) {

  if((connectionString == null) || connectionString.trim() == "") {
    throw new IllegalArgumentException("ConnectionStringPropertyMap Constructor Error: " +
      "Argument connectionString cannot be null")

    var input = connectionString
    if (input != "") {
      if (!input.endsWith(connectionStringPropertySeparator)) {
        input = input + connectionStringPropertySeparator
      }

      connectionStringPropertyPattern.findAllIn(input).matchData.foreach(
        m => {
          val group: String = m.group("Section")
          val propertyName: String = m.group("PropertyName").trim()
          val propertyValue: String = m.group("PropertyValue").trim()
          connectionProperties + (propertyName -> propertyValue)
        }
      )
    }
  }

  private val connectionStringPropertyPattern = "(?<Section>(?<PropertyName>(([^;=])*))[=](?<PropertyValue>([^;=])*)[;])".r
  private val connectionStringPropertySeparator: String = ";"
  private val connectionProperties: mutable.LinkedHashMap[String,String] = new mutable.LinkedHashMap[String,String]

  def contains(property: (String, String)): Boolean = {
    connectionProperties.contains(property._1)
  }

  def contains(propertyName: String): Boolean = {
    connectionProperties.contains(propertyName)
  }

  def indexOf(property: (String, String)): Int = {
    val l = connectionProperties.keysIterator.toList
    l.indexOf(property._1)
  }

  def indexOf(propertyName: String): Int = {
    val l = connectionProperties.keysIterator.toList
    l.indexOf(propertyName)
  }

  def getPropertyValue(propertyName:String): (String) = {
    val opt = connectionProperties.get(propertyName)
    opt.get
  }

  def getProperty(index: Int): (String, String) = {
    val l = connectionProperties.toList
    l(index)
  }
}
