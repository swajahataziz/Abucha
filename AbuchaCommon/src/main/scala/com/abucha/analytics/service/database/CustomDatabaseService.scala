package com.abucha.analytics.service.database

import com.abucha.analytics.model.database.DatabaseHost
import com.abucha.analytics.model.database.types.CustomDatabase
import org.joda.time.DateTime

/**
  * Created by Syed.Aziz on 25/07/2017.
  */
class CustomDatabaseService(driverClass: String,
                            dbType: String = "Custom",
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
                            jdbcUrl: String,
                            query: String) extends DatabaseService[CustomDatabase]{

  override def createDatabase(): CustomDatabase = new CustomDatabase(id, path, urlPath, name,createdBy,description,createdDate,
                                                          editable,deletable,dbName, driverClass, jdbcUrl, query)

  override def parse(driverClass: String, jdbcUrl: String, database: CustomDatabase): DatabaseHost = {
    new DatabaseHost(null,null,0)
  }

  override def formatURL(dbHost: DatabaseHost, database: CustomDatabase): String = database.jdbcUrl

  def getDriver(database: CustomDatabase): String = database.driverClass

  override def supportsDriver(var1: String) = true

  override def isDriverInstalled = true

  override def getDefaultPort = 0
}
