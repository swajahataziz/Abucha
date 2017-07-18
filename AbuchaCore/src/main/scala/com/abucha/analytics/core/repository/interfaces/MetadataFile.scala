package com.abucha.analytics.core.repository.interfaces
/**
  * Created by Syed.Aziz on 22/04/2017.
  */

import java.time._

trait MetadataFile extends File with FolderItem {

  val lastModified: LocalDateTime
  val objectCaption: String
  val objectKey: String
  val objectType: String

}
