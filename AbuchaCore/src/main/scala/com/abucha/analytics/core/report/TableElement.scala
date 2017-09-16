package com.abucha.analytics.core.report

import java.awt.Insets
import java.text.Format

trait TableElement extends ReportElement {

  val presenter: Presenter
  val format: Format
  val tableWidth: Double
  val fixedWidth: Array[Int]
  val layout: Int
  val padding: Insets
  val tableAdvance: Int
  val maxColWidth: Float
  val keepSpanTogether: Boolean
  val keepGroupTogether: Boolean



}
