package com.abucha.analytics.core.report

import java.awt.{Color, Font}

trait ReportElement extends Serializable with Cloneable {
  val id: String
  val name: String
  val alignment: Int
  val indent: Double
  val font: Font
  val colour: Color
  val background: Color
  val spacing: Int
  val isVisible: Boolean
  val isHideOnPrint: Boolean
  val keepWithNext: Boolean
  val script: String
  val onClick: String
  val target: String
  val property: String
  val propertyName: String
  val userObject: AnyRef
  val cssclass: String
  val alignmentByUser: Boolean
  val userAlignment: Option[Int]
  val fontByUser: Boolean
  val userFont: Option[Font]
  val foregroundByUser: Boolean
  val userForeground: Option[Color]
  val backgroundByUser: Boolean
  val userBackground: Color

  def propertyNames: Array[String]

  def context(reportElement: ReportElement)

  def getType: String

  def preferredSize: Size

  def cssType: String

  def applyStyle(): Unit
}
