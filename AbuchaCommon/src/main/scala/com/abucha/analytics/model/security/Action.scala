package com.abucha.analytics.model.security

trait Action {
  val urlPath: String

  case class DashboardAction(urlPath: String = "/") extends Action

  case class DataSetAction(urlPath: String = "_asset/") extends Action

  case class ViewAction(urlPath: String = "_view") extends Action

  case class WorkSheetAction(urlPath: String = "_worksheet") extends Action

  case class TabeAction(urlPath: String = "_table") extends Action

  case class BookmarkAction(urlPath: String = "_bookmark") extends Action

  case class ScheduleAction(urlPath: String = "_schedule") extends Action

  case class ServerAction(urlPath: String = "_server") extends Action

  case class LogAction(urlPath: String = "_log") extends Action

  case class PermissionsAction(urlPath: String = "_permissions") extends Action

  case class RepositoryAction(urlPath: String = "_repo") extends Action

  case class UserAction(urlPath: String = "_user") extends Action

}
