package com.abucha.analytics.model.database.types

import com.abucha.analytics.model.database.Database
import org.joda.time.DateTime

class SQLServer (val instanceName: String,
                 id: String,
                 path: String,
                 urlPath: String,
                 name: String,
                 createdBy: String,
                 description: String,
                 createdDate: DateTime,
                 editable: Boolean,
                 deletable: Boolean,
                 dbName: String,
                 val driverClass: String,
                 val jdbcUrl:String,
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
