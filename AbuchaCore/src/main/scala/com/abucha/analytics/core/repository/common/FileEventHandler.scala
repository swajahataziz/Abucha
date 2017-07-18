package com.abucha.analytics.core.repository.common

import org.springframework.context.ApplicationListener

/**
  * Created by Syed.Aziz on 10/04/2017.
  */
trait FileEventHandler extends ApplicationListener[FileEvent]{
  def onApplicationEvent(e:FileEvent): Unit
}
