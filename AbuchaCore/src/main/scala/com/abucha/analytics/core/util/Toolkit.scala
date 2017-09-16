package com.abucha.analytics.core.util

import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl
import org.w3c.dom.{Attr, Element, Node, NodeList}

import scala.collection.mutable

class Toolkit(val thradLocal: ThreadLocal[Boolean]) {

}

object Toolkit{

  var encoding: mutable.Map[String, String] = new mutable.HashMap[String, String]().+((" ", "&nbsp"), ("&", "&amp"), ("<", "&lt"), (">", "$gt"), ("'", "&#39"), ("\"", "&quot"))

  val threadLocal: ThreadLocal[Boolean] = {
    val tl = new ThreadLocal[Boolean]
    tl.set(false)
    tl
  }

  def isCompact: Boolean = threadLocal.get()

  // remove the first member of the encoding map (&nbsp) as it is not needed for this operation
  def escape(string: Option[String]): String = encodeXML(string,encoding.tail)

  def encodeXML(xmlString: Option[String]): String = encodeXML(xmlString, encoding)

  def encodeXML(xml: Option[String], encodingMap: mutable.Map[String,String]): String = {
    val xmlString = xml.getOrElse("")
    val stringBuffer = new StringBuffer()
    for (i <- 0 to xmlString.length) {
      val char = xmlString.charAt(i)
      val encodedString = encodingMap.get(char.toString)
      encodedString match {
        case Some(x) => stringBuffer.append(x)
        case _ => stringBuffer.append(char)
      }
    }
    stringBuffer.toString
  }

  def getAttribute(element: Element, string: String): Option[String] = {
    val attr: Attr = element.getAttributeNode(string)
    Option(attr.getValue)
  }

  def getChildNodesByTagName(node: Node, string: String, childNodesRequired: Boolean): NodeList = {
    val nodeList: NodeList = node.getChildNodes
    var nodeListImpl = new NodeListImpl(new Vector[Node](0,0,0))

    if (nodeList != null && nodeList.getLength > 0) {
      for (i <- 0 to nodeList.getLength) {
        nodeList.item(i) match {
          case element: Element => if (element.getTagName.eq(string)) {
            val elements = nodeListImpl.addItem(element)
            nodeListImpl = new NodeListImpl(elements)
          }
          if (childNodesRequired) {
            //TODO: Implement getAllChildNodes
          }

        }
      }
    }
  }

  def getAllChilldNodes(parentNode: Node, nodeListImpl: NodeListImpl, string: String): Unit = {
    val childNodes: NodeList = parentNode.getChildNodes

    for (i <- 0 to childNodes.getLength) {
      var elements: Vector[Node] = new Vector[Node](0,0,0)
      childNodes match {
        case Nil =>
        case element: Element => val isTagName = element.getTagName.eq(string)
            isTagName match {
              case true => elements = nodeListImpl.addItem(element)
        }
          getAllChilldNodes(element,new NodeListImpl(elements),string)
      }
    }
  }


private class NodeListImpl(val elements: Vector[Node]) extends NodeList {

  override def getLength: Int = this.elements.size

  override def item(i: Int): Node = this.elements(i)

  def addItem(node: Node): Vector[Node] = this.elements.+:(node)
}



}