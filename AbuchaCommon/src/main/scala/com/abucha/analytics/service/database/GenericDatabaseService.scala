package com.abucha.analytics.service.database

import com.abucha.analytics.model.database.{Database, DatabaseHost}

/**
  * Created by Syed.Aziz on 25/07/2017.
  */
trait GenericDatabaseService[A <: Database] extends DatabaseService[A] {

  def parse(var1: String, var2: String, var3: A): DatabaseHost = {
    this.parseURL(var2, var3)
  }
  def parseURL(var1: String, var2: A): DatabaseHost

  override def supportsDriver(var1: String): Boolean

  override def isDriverInstalled: Boolean = true
  /*TODO
  def boolean isDriverInstalled() {
    return JDBCHandler.isDriverAvailable(this.driverClass)
   */


}
