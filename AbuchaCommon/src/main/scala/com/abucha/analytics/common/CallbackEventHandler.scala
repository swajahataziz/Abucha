package com.abucha.analytics.common

import org.springframework.context.ApplicationListener

/**
  * Created by Syed.Aziz on 09/04/2017.
  * DataCallbackEventHandler in S
  */
trait CallbackEventHandler extends ApplicationListener[CallbackEvent] {

  override def onApplicationEvent(e: CallbackEvent): Unit
}
