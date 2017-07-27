package com.abucha.analytics.service.database

import java.util

import com.abucha.analytics.model.database.{Database, DatabaseDriver, DatabaseHost, DatabaseType}
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner

import scala.collection.JavaConversions
import scala.collection.immutable.HashMap

/**
  * Created by Syed.Aziz on 25/07/2017.
  */
trait DatabaseService[A <: Database] {

  def createDatabase(url: String): A
  abstract def parseUrl(var1: String): DatabaseHost
  abstract def formatURL(var1: DatabaseHost, var2: A): String
  abstract def supportsDriver(var1: String): Boolean
  abstract def isDriverInstalled: Boolean
  abstract def getDefaultPort: Int

  object DatabaseService {
    private val scanResult = new FastClasspathScanner("com.abucha.analytics.service.database").scan()
    private val classes = JavaConversions.asScalaBuffer(scanResult.getNamesOfAllClasses).toList

    def buildTypes(classes: List[String]): HashMap[String, DatabaseType[_ <: Database]] = {

      def mapAccumulator(classes: List[String],
                         accum: HashMap[String, DatabaseType[_ <: Database]]):
      HashMap[String, DatabaseType[_ <: Database]] = {
        classes match {
          case Nil => types
          case x :: tail => {
            val clazz: Class[_] = scanResult.classNameToClassRef(x)
            val dbType: DatabaseType[_ <: Database] = clazz.newInstance.asInstanceOf[DatabaseType[_ <: Database]]
            mapAccumulator(tail,accum + (x -> dbType))
          }
        }
      }
      mapAccumulator(classes,new HashMap[String, DatabaseType[_ <: Database]])
    }

    val types: HashMap[String, DatabaseType[_ <: Database]] = buildTypes(classes)

    def getDatabaseType(var0: String): DatabaseType[_ <:Database] = {
      types.get(var0).asInstanceOf[DatabaseType[_ <: Database]]
    }

    def getDatabaseTypeForDriver(var0: String): DatabaseType[_ <: Database] = {
      var var1 = types.get("CUSTOM").asInstanceOf[DatabaseType[_ <: Database]]
      val var2 = types.values.iterator
      while (var2.hasNext) {
        val var3 = var2.next
        if (var3.supportsDriver(var0) && !("CUSTOM" == var3.dbType)) {
          var1 = var3
        }
      }
      var1
    }

    def getDrivers: Array[DatabaseDriver] = {
      val var0 = new util.ArrayList[DatabaseDriver]
      val var1 = types.values.iterator
      while ( {
        var1.hasNext
      }) {
        val var2 = var1.next
        val var3 = new DatabaseDriver(var2.dbType + " Driver",
          true,
          false,
          var2.dbType,
          var2.isDriverInstalled,
          var2.getDefaultPort
        )
        var0.add(var3)
      }
      var0.toArray(new Array[DatabaseDriver](var0.size))
    }

  }

}