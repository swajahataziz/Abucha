package com.abucha.analytics.core.repository.serialization

import com.abucha.analytics.core.repository.Folder
import net.liftweb.json.JsonDSL._
import net.liftweb.json._

/**
  * Created by Syed.Aziz on 24/04/2017.
  */
class FolderWriter extends Writer {

  override def write(anyRef: AnyRef): Unit = {
    val folder: Folder = anyRef.asInstanceOf[Folder]
    val json = "Folder" -> (
      "Name" -> folder.
    )


  }

}
