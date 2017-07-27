package com.abucha.analytics.service.database

import java.util.regex.{Matcher, Pattern}

import com.abucha.analytics.model.database.DatabaseHost
import com.abucha.analytics.model.database.types.SQLServer
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

  override def parseUrl(url: String) = {
    val matcher: Matcher = pattern.matcher(url)
    var hostName: String = null
    var instanceName: String = null
    var port: Int = 0
    if (matcher.matches()) {
      hostName = matcher.group(1)
      if (!matcher.group(2).isEmpty) {
        instanceName = matcher.group(2)
      }
      if (!matcher.group(3).isEmpty) {
        port = matcher.group(3).toInt
      }
    }
    new DatabaseHost(hostName,null,port)
  }


}
