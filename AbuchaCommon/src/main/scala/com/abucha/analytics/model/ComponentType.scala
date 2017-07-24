package com.abucha.analytics.model

/**
  * Created by syed on 7/19/17.
  */
import enumeratum._

sealed trait ComponentType extends EnumEntry {
  object ComponentTypes extends Enum[ComponentType] {
    val values: IndexedSeq[ComponentType] = findValues

    case object Database extends ComponentType
    case object DataSource extends ComponentType
    case object Folder extends ComponentType
  }

}
