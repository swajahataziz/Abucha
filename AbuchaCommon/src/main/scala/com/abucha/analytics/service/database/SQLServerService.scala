package com.abucha.analytics.service.database

import java.util.regex.{Matcher, Pattern}

import com.abucha.analytics.model.database.DatabaseHost
import com.abucha.analytics.model.database.types.{Oracle, SQLServer}
import org.joda.time.DateTime
/**
  * Created by Syed.Aziz on 26/07/2017.
  */
class SQLServerService(val driverClass: String = "com.microsoft.sqlserver.jdbc.SQLServerDriver",
                       val dbType: String = "SqlServer",
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
                       pattern: Pattern = Pattern.compile("^jdbc:sqlserver://([^\\\\\\\\:]+)(?:\\\\\\\\([^:]+))?(?::(\\\\d+))?$"),
                       urlFormat: String = "jdbc:sqlserver://%s:%d",
                       urlFormatWInstanceName: String = "jdbc:sqlserver://%s\\%s:%d"
                      ) extends GenericDatabaseService[SQLServer]{

  val databaseHost: DatabaseHost = parseUrl(this.jdbcUrl)

  override def parseUrl(url: String) = {
    val matcher: Matcher = pattern.matcher(url)
    var hostName: String = null
    var port: Int = 0
    var instanceName: Option[String] = None
    if (matcher.matches()) {
      hostName = matcher.group(1)
      if (!matcher.group(2).isEmpty) {
        instanceName = Some(matcher.group(2))
      }
      if (!matcher.group(3).isEmpty) {
        port = matcher.group(3).toInt
      }
    }
    new DatabaseHost(hostName,null,port, instanceName)
  }

  override def createDatabase(url: String): SQLServer = new SQLServer(instanceName, this.id, this.path, this.urlPath, this.name,
    this.createdBy, this.description, this.createdDate, this.editable, this.deletable, this.dbName, this.driverClass,
    url, this.query)

  override def formatURL(databaseHost: DatabaseHost, sqlServer: SQLServer): String =
    sqlServer.instanceName match {
      case None => String.format(urlFormat,databaseHost.hostName, databaseHost.port)
      case _ => String.format(urlFormat,databaseHost.hostName, sqlServer.instanceName.get,databaseHost.port)
    }

  override def getDefaultPort: Int = 1433

  override def supportsDriver(driver: String): Boolean = this.driverClass.eq(driver)

  def instanceName: Option[String] = this.databaseHost.property
}