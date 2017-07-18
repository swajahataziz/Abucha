package com.abucha.analytics.core.model

import org.joda.time.DateTime

/**
  * Created by Syed.Aziz on 27/05/2017.
  */
trait Component {

  def componentType: String
  def componentInfo: ComponentInfo
  def componentMetaData: ComponentMetaData
}