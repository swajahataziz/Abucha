package com.abucha.analytics.core.util

class Toolkit(val thradLocal: ThreadLocal[Boolean]) {

}

object Toolkit{

  val threadLocal: ThreadLocal[Boolean] = {
    val tl = new ThreadLocal[Boolean]
    tl.set(false)
    tl
  }

  def isCompact: Boolean = threadLocal.get()
}