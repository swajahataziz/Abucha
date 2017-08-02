package com.abucha.analytics.core.util

import java.io.PrintWriter

import org.w3c.dom.Element

trait XMLSerialisable {

  def write(writer: PrintWriter)

  def parse(element: Element)

}
