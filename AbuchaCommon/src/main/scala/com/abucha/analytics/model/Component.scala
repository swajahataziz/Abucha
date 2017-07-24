package com.abucha.analytics.model

import org.joda.time.DateTime

/**
  * Created by syed on 7/18/17.
  */
abstract class Component(id: String,
                         path: String,
                         urlPath: String,
                         name: String,
                         createdBy: String,
                         description: String,
                         createdDate: DateTime,
                         editable: Boolean,
                         deletable: Boolean) {
}
