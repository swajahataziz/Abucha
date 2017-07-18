package com.abucha.analytics.common.collection

import org.springframework.context.ApplicationEvent

/**
  * Created by Syed.Aziz on 26/04/2017.
  */
class DictionaryEvent(source:AnyRef,
                      crudOp: CrudOp.EnumVal,
                      dictionaryElement: DictionaryElement) extends ApplicationEvent {
}
