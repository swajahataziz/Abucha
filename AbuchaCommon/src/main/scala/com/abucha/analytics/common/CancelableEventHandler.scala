package com.abucha.analytics.common

import org.springframework.context.ApplicationListener


/**
  * Created by Syed.Aziz on 09/04/2017.
  */

trait CancelableEventHandler extends ApplicationListener[CancelableEvent] {

  override def onApplicationEvent(e: CancelableEvent): Unit
}
