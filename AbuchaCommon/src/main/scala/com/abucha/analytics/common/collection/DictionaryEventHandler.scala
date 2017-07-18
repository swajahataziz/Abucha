package com.abucha.analytics.common.collection

import org.springframework.context.ApplicationListener

/**
  * Created by Syed.Aziz on 26/04/2017.
  */
trait DictionaryEventHandler extends ApplicationListener[DictionaryEvent] {

  override def onApplicationEvent(event: DictionaryEvent)

}
