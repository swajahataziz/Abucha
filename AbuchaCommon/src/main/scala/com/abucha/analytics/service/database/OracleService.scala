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
                     query: String,
                     pattern: Pattern = Pattern.compile("^jdbc:oracle:thin:@([^:]+):(\\d+):(.+)$"),
                     urlFormat: String = "jdbc:oracle:thin:@%s:%d:%s"
                    )extends GenericDatabaseService[Oracle] {

  var sid: String = _

  def parseUrl(url: String): DatabaseHost = {
    val matcher: Matcher = pattern.matcher(url)
    var host: String = null
    var port: Int = 0

    if (matcher.matches()){
      host = matcher.group(1)
      port = matcher.group(2).toInt
      sid = matcher.group(3)
    }
    new DatabaseHost(host, None, port, None)
  }

  override def createDatabase(url: String): Oracle = new Oracle(this.sid, this.id, this.path, this.urlPath, this.name,
    this.createdBy, this.description, this.createdDate, this.editable, this.deletable, this.dbName, this.driverClass,
    url, this.query)

  override def formatURL(databaseHost: DatabaseHost, db: Oracle): String =
    String.format(urlFormat,databaseHost.hostName, databaseHost.port, db.sid)

  override def getDefaultPort: Int = 1521

  override def supportsDriver(driver: String): Boolean = this.driverClass.eq(driver)
}
