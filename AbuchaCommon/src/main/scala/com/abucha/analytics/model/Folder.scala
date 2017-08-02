package com.abucha.analytics.model

import org.joda.time.DateTime

case class Folder(id: String,
             path: String,
             urlPath: String,
             name: String,
             createdBy: String,
             description: String,
             createdDate: DateTime,
             editable: Boolean,
             deletable: Boolean,
             parentPath: String,
             parentFolderCount: Int) extends Component(id,
  path,
  urlPath,
  name,
  createdBy,
  description,
  createdDate,
  editable,
  deletable) {

}
