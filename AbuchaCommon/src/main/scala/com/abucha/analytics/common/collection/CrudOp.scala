package com.abucha.analytics.common.collection

/**
  * Created by Syed.Aziz on 26/04/2017.
  */
object CrudOp {
  sealed trait EnumVal
  case object Insert extends EnumVal
  case object Update extends EnumVal
  case object Delete extends EnumVal

  val crudOps = Seq(Insert,Update,Delete)
}
