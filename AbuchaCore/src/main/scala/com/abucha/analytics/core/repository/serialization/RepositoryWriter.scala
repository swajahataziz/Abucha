package com.abucha.analytics.core.repository.serialization

import com.abucha.analytics.core.repository.Repository
import net.liftweb.json.JsonDSL._
import net.liftweb.json._

/**
  * Created by Syed.Aziz on 24/04/2017.
  */
class RepositoryWriter extends Writer {

  def write(anyObject: AnyRef): Unit = {

    val repository: Repository = anyObject.asInstanceOf[Repository]

    val json =
      "RepositoryConfig" -> (
        "ConfigData" -> (
          "Folders" ->
            ("FolderCount" -> repository.folders.count.toString)
          ) ~
          (
            "Files" ->
              ("FileCount" -> repository.files.count.toString)
          )
        )
    compactRender(json)
  }
}
