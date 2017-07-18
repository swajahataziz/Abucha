package com.abucha.analytics.core.model

import org.joda.time.DateTime

/**
  * Created by Syed.Aziz on 28/05/2017.
  */
case class DataSource(componentType: String = "DataSet",
                      componentInfo: ComponentInfo,
                      componentMetaData: ComponentMetaData) extends Component {
}

