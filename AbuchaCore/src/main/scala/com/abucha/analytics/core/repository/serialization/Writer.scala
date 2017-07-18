package com.abucha.analytics.core.repository.serialization

/**
  * Created by Syed.Aziz on 24/04/2017.
  */
trait Writer {
  def write(anyRef: AnyRef): Unit
}
