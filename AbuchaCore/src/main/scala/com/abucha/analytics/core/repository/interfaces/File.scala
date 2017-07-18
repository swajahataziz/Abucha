package com.abucha.analytics.core.repository.interfaces

import java.io.{FileInputStream, FileOutputStream}

/**
  * Created by Syed.Aziz on 10/04/2017.
  */
trait File {
  def beginRead: FileInputStream
  def beginWrite: FileOutputStream
  def endRead(fileInputStream: FileInputStream)
  def endWrite(fileOutputStream: FileOutputStream)
  def fireCustomEvent(anyRef: AnyRef)
  def objectContent
  def stringContent: String
  def writeStringContent(string: String)
  def isEmpty: Boolean
}