package com.abucha.analytics.model.database.types

import com.abucha.analytics.model.database.{Database, DatabaseHost, DatabaseType}

/**
  * Created by syed on 7/23/17.
  */
trait GenericDatabase[A <: Database] extends DatabaseType[A] {

  def parse(var1: String, var2: String, var3: A): DatabaseHost = {
    this.parseURL(var2, var3)
  }
  def parseURL(var1: String, var2: A): DatabaseHost

  override def supportsDriver(var1: String): Boolean = {
    this.driverClass.eq(var1)
  }

  /*TODO
  def boolean isDriverInstalled() {
    return JDBCHandler.isDriverAvailable(this.driverClass)
   */
}
