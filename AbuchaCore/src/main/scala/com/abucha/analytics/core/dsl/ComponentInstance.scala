package com.abucha.analytics.core.dsl

import com.abucha.analytics.core.util.XMLSerialisable
import com.abucha.analytics.core.util.dto.DataTransferable

trait ComponentInstance extends Serializable with Cloneable with XMLSerialisable with DataTransferable {

  def clone(): AnyRef
}
