package com.abucha.analytics.core.util.dto

trait DataTransferable {

  def read(dto: BaseDto): Unit

  def write(): BaseDto
}
