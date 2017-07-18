package com.abucha.analytics.core.repository.common

import com.abucha.analytics.core.repository.Folder
/**
  * Created by Syed.Aziz on 24/04/2017.
  */
abstract class FolderItem {
  val title: String
  val folder: Folder
  val isDisposed: Boolean
  val name: String
  val propertyMap: ConnectionStringPropertyMap



}
