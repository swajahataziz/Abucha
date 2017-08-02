package com.abucha.analytics.core.util

import java.io.{DataInputStream, DataOutputStream}

trait DataSerialisable {

  def write(dataOutputStream: DataOutputStream)

  def parse(dataInputStream: DataInputStream)

}
