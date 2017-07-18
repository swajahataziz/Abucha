package com.abucha.analytics.core.repository.common

import java.util

import com.abucha.analytics.core.repository.Folder
import org.apache.catalina.startup.ClassLoaderFactory.Repository

/**
  * Created by Syed.Aziz on 27/05/2017.
  */
class FileMap(repository: Repository, folder:Folder) {


  var array: util.ArrayList[AnyVal] = new util.ArrayList[AnyVal]()
  var hashtable: util.Hashtable[AnyVal,AnyVal] = new util.Hashtable[AnyVal, AnyVal]()
  var caseSensitive: Boolean = false

  def this(repository: Repository, folder: Folder) = {
    this(repository,folder)
  }


}
