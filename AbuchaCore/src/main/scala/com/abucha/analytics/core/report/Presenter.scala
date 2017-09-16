package com.abucha.analytics.core.report

import java.awt.{Color, Dimension, Font, Graphics}

trait Presenter extends Serializable {

  val font: Font
  val background: Color
  val displayName: String

  def paint(graphics: Graphics, anyRef: AnyRef, coord1: Int, coord2: Int, coord3: Int, coord4: Int): Unit

  def getPreferredSize(anyRef: AnyRef): Dimension

  def isPresenterOf(any: Any): Boolean

  def isPresenterOf(anyRef: AnyRef): Boolean

  def filled: Boolean

  def isRawDataRequired: Boolean
}
