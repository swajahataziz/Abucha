package com.abucha.analytics.core.util

import java.security.Principal

import scala.collection.immutable.HashMap

/**
  * Created by Syed.Aziz on 22/08/2017.
  */
class IndexFactory(val indexThreadMap: Map[ThreadGroup,Index], indexMap: Map[String, Index]) {

  def updateThreadIndexMap(index: Index): Map[ThreadGroup, Index] = {
    updateThreadIndexMap(Thread.currentThread().getThreadGroup, index)}

  def updateThreadIndexMap(threadGroup: ThreadGroup, index: Index): Map[ThreadGroup, Index] = {
    this.indexThreadMap + (threadGroup -> index)
  }

  def getIndex: Index = {
    val thread: Thread = Thread.currentThread()
    val threadGroup: ThreadGroup = thread.getThreadGroup
    val index: Option[Index] = indexThreadMap.get(threadGroup)
    index match {
      case None =>
    }

  }

  def getIndex(principal: Principal, indexType: String): Index = {
    if (indexType.eq(Index.default)) {

    }
  }

  def getDefaultIndex(indexName: String): Index = {

  }

  def getDefaultIndex(indexName: String, propertyName: String): Index = {

  }

}
object IndexFactory {

}
