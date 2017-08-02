package com.abucha.analytics.model

import org.joda.time.DateTime

case class Dashboard(id: String,
                     path: String,
                     urlPath: String,
                     name: String,
                     createdBy: String,
                     description: String,
                     createdDate: DateTime,
                     editable: Boolean,
                     deletable: Boolean,
                     refreshInterval: Int,
                     onReport: Boolean,
                     parentPath: String,
                     parentFolderCount: Int) extends Component(id,
  path,
  urlPath,
  name,
  createdBy,
  description,
  createdDate,
  editable,
  deletable){

}
