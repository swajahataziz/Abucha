package com.abucha.analytics.controller

import javax.servlet.http.HttpServletRequest

import org.springframework.util.AntPathMatcher
import org.springframework.web.servlet.HandlerMapping

trait BaseController {

  def getPathFromPattern(request: HttpServletRequest): Option[String] = {
    var fullPath: Option[String] = Option(request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).asInstanceOf[String])
    var pattern: Option[String] = Option(request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).asInstanceOf[String])
    var path: Option[String] = None

    fullPath match {
      case None => path
      case Some(s) => pattern match {
        case None => path
        case Some(x) =>
          Option(new AntPathMatcher().extractPathWithinPattern(x, s)).getOrElse("/")

          val p = Option(new AntPathMatcher().extractPathWithinPattern(x, s))
          p match {
            case None => Option("/")
            case _ => p
          }
      }
    }
  }
}
