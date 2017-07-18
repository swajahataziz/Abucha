package com.abucha.analytics.common

import org.springframework.context.ApplicationEvent

/**
  * Created by Syed.Aziz on 09/04/2017.
  */
class CancelableEvent(source: AnyRef, cancellationFlag: Boolean) extends ApplicationEvent {
}
