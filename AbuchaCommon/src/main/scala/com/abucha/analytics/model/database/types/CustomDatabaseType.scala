package com.abucha.analytics.model.database.types

import com.abucha.analytics.model.database.{Database, DatabaseType}
import org.joda.time.DateTime

/**
  * Created by syed on 7/23/17.
  */
class CustomDatabaseType(driverClass:String, dbType: String = "Custom")
  extends DatabaseType[CustomDatabase](driverClass, dbType){

  override def createDatabase() = new CustomDatabase()







}
