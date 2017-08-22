package com.abucha.analytics.core.util

class Toolkit {

}

object Toolkit{
  var threadLocal: ThreadLocal[Boolean] = null

  def isCompact(): Boolean = {
    threadLocal.get()
  }

  def getCompact(isCompact: Boolean): Unit = {
    threadLocal.set(isCompact)
  }
}