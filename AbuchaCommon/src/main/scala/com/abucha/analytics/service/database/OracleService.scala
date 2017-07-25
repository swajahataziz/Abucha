package com.abucha.analytics.service.database

import java.util.regex.{Matcher, Pattern}

import com.abucha.analytics.model.database.DatabaseHost
import com.abucha.analytics.model.database.types.Oracle
import org.joda.time.DateTime

class OracleService (val driverClass: String = "oracle.jdbc.OracleDriver",
                     val dbType: String = "Oracle",
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
                     query: String,
                     pattern: Pattern = Pattern.compile("^jdbc:oracle:thin:@([^:]+):(\\d+):(.+)$"),
                     format: String = "jdbc:oracle:thin:@%s:%d:%s"
                    )extends GenericDatabaseService[Oracle] {

  var sid: String = _

  override def parseURL(url: String, db: Oracle): DatabaseHost = {
    val matcher: Matcher = pattern.matcher(url)
    var host: String = null
    var port: Int = 0

    if (matcher.matches()){
      host = matcher.group(1)
      port = matcher.group(2).toInt
      sid = matcher.group(3)
    }
    new DatabaseHost(host, null, port)
  }

  override def createDatabase(): Oracle = new Oracle(this.sid, id, path, urlPath, name,createdBy,description,createdDate,
                                                      editable,deletable,dbName, driverClass, jdbcUrl, query)

  override def formatURL(databaseHost: DatabaseHost, db: Oracle): Unit =
    String.format(format,databaseHost.hostName, databaseHost.port, db.sid)

  override def getDefaultPort: Int = 1521

  override def supportsDriver(driver: String): Boolean = this.driverClass.eq(driver)
}
