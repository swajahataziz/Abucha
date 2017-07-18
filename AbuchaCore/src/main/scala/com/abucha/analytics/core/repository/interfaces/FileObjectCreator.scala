package com.abucha.analytics.core.repository.interfaces

import java.io.FileInputStream

/**
  * Created by Syed.Aziz on 10/04/2017.
  */
trait FileObjectCreator {
  def createObject(fileInputStream: FileInputStream): AnyRef
}
