package com.abucha.analytics.model.database.types

import com.abucha.analytics.model.database.Database
import org.joda.time.DateTime

/**
  * Created by syed on 7/23/17.
  */
case class CustomDatabase(override val id: String,
                          override val path: String,
                          override val urlPath: String,
                          override val name: String,
                          override val createdBy: String,
                          override val description: String,
                          override val createdDate: DateTime,
                          override val editable: Boolean,
                          override val deletable: Boolean,
                          override val dbName: String,
                          driverClass: String,
                          jdbcUrl:String,
                          query: String) extends Database(id,
                                                    path,
                                                    urlPath,
                                                    name,
                                                    createdBy,
                                                    description,
                                                    createdDate,
                                                    editable,
                                                    deletable,
                                                    dbName) {


}
