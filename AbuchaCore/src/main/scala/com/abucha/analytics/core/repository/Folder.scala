package com.abucha.analytics.core.repository

import com.abucha.analytics.core.repository.common.{FolderItem, MapEntry, PropertyMap}

/**
  * Created by Syed.Aziz on 10/04/2017.
  */
class Folder (repository: Repository, name: String, caption: String) {

  var properties: PropertyMap = new PropertyMap

  def add(property:MapEntry): Unit = {
    properties.add(property)
  }

}
