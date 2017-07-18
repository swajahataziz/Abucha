package com.abucha.analytics.core.model

import org.joda.time.DateTime

/**
  * Created by Syed.Aziz on 27/05/2017.
  */
case class DataSet(componentType: String = "DataSet",
                   componentInfo: ComponentInfo,
                   componentMetaData: ComponentMetaData) extends Component {
}
