package com.abucha.analytics.core.util

import java.util.logging.Logger
import java.util.{Locale, ResourceBundle}

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet

/**
  * Created by Syed.Aziz on 22/08/2017.
  */
class Index (val resource: String,
             resource2: String,
             locale: Locale,
             bundle: ResourceBundle,
             bundle2: ResourceBundle,
             keys: ObjectOpenHashSet[String],
             keys2: ObjectOpenHashSet[String]) {

}
object Index {
  val default: String = "_ABUCHA_DEFAULT_JOURNAL_"
  val report: String = "_ABUCHA_REPORT_JOURNAL_"
  val defaultResource: String = "com/abucha/analytics/core/util/sprinter"
  val defaultBundle: String = "defaultRBundle"
  val log: Logger = Logger.getLogger(Index.getClass.getName)
}