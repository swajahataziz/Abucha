package com.abucha.analytics.model

import org.joda.time.DateTime

/**
  * Created by syed on 7/19/17.
  */
case class DataSource (id: String,
                       path: String,
                       urlPath: String,
                       name: String,
                       createdBy: String,
                       description: String,
                       createdDate: DateTime,
                       editable: Boolean,
                       deletable: Boolean) extends Component (id,
                                                              path,
                                                              urlPath,
                                                              name,
                                                              createdBy,
                                                              description,
                                                              createdDate,
                                                              editable,
                                                              deletable) {
}
