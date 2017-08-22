package com.abucha.analytics.core.util

import java.security.Principal

class Register {




trait RegisterFactory {
  def getCatalog(principal: Principal, string: String)
}

}

object Register {

  def getRegister: Register = {
    getRegister(None)
  }

  def getRegister(principal: Option[Principal]): Register = {
    getRegister(principal, None)
  }

  def getRegister(principal: Option[Principal], string: Option[String]): Register = {

  }


}
