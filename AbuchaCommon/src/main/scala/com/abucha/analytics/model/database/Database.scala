package com.abucha.analytics.model.database

import com.abucha.analytics.model.{Component, ComponentType}
import org.joda.time.DateTime

/**
  * Created by syed on 7/19/17.
  */
case class Database(id: String,
               path: String,
               urlPath: String,
               name: String,
               createdBy: String,
               description: String,
               createdDate: DateTime,
               editable: Boolean,
               deletable: Boolean,
                    dbName: String) extends Component (id,
                                                      path,
                                                      urlPath,
                                                      name,
                                                      createdBy,
                                                      description,
                                                      createdDate,
                                                      editable,
                                                      deletable) {
}
