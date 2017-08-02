package com.abucha.analytics.model.security

sealed trait Role {
  case class All(name: String) extends Role{

  }

  case class Guest(name: String) extends Role {

  }

  case class Administrator(name: String) extends Role {

  }

  case class PowerUser(name: String) extends Role {

  }

  case class User(name: String) extends Role {

  }
}
