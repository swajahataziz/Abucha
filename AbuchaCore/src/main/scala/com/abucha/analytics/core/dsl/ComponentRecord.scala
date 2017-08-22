package com.abucha.analytics.core.dsl.component

import java.io.PrintWriter
import java.util.{Properties}

import com.abucha.analytics.core.dsl.ComponentInstance
import com.abucha.analytics.core.dsl.component.ComponentRecord.ComponentType
import com.abucha.analytics.core.util.{DataSerialisable, ExtendedToolkit, Toolkit}
import com.abucha.analytics.core.util.dto.DataTransferable
import org.joda.time.DateTime
import org.w3c.dom.Element

import scala.collection.BitSet

class ComponentRecord(val scope: Int, val componentType: ComponentType, val user: String, val componentPath: String,
                      val alias: Option[String], val componentProperties: Properties, val cratedByUser: String,
                      val dateCrated: DateTime, val modifiedByUser: String, val dateModified: DateTime, val id: Int,
                      val hash: Int, val privatePath: String, val privateComponentRecord: ComponentRecord)
                      //TODO val collator: Collator = Locale.getDefault().getLanguage.equals("en"))?null:....
  extends ComponentInstance with Comparable[AnyRef] with DataSerialisable with DataTransferable{







  def write(writer: PrintWriter): Unit = {
    val isCompact:Boolean = Toolkit.isCompact()
    val componentHeader: String = if (!isCompact) {
      " class=\"com.abucha.analytics.core.dsl.ComponentRecord\""
    } else ""
    writer.print("<assetEntry"+componentHeader + " scope=\"" + this.scope + "\" type=\"" + this.componentType.id + "\">")
    writer.print("<path>")
    writer.print("<![CDATA[" + this.componentPath + "]]>")
    writer.print("</path>")
    if (!isCompact) {
      alias match {
        case Some(`alias`) => writer.print("<alias>")
          writer.print("<![CDATA[" + alias + "]]>")
          writer.print("</alias>")
        case None => ""
      }
      writer.print("<description>")
      writer.print("<![CDATA[" )


    }






  }

  /*TODO: Check if compareTo is really needed in Scala, especially with immutable objects*/
  def compareTo(o: AnyRef): Int = {
    if(!o.isInstanceOf[ComponentRecord]) {
      1
    }
    else 1
  }
}

object ComponentRecord{
  val reportId: String = "report.id"
  val documentType: String = "document.type"
  val documentDescription: String = "document.description"
  val reportDataSource: String = "report.datasource"
  val entryPaths: String = "entry.paths"
  val queryType: String = "query.type"
  val currentQuery: String = "query.current"
  val normal: String = "normal"
  val preAggregate: String = "pre-aggregate"
  val cube: String = "cube"
  val cubeColumnType = "cube.column.type"
  val dataSourceType: String = "datasource.type"
  val dimensions: Int = 0
  val measures: Int = 1
  val dateDimensions = 2
  private val nullString = "_Null_"
  private val clazz = "class=\"com.abucha.analytics.core.dsl.component.ComponentRecord\""


  /*TODO: Split into two lines */
  def isIgnoredProperty(property: String): Boolean = {
    property.eq("Tooltip") || property.eq("entry.paths") || property.eq("preview") || property.eq("localStr") || property.eq("_oneoff_") || property.eq("description")
  }

  //TODO: method to create asset entry based on a complex string containing multiple object variables
  def createComponentRecord(assetEntryString: String): ComponentRecord = {
    return new ComponentRecord(0,new Unknown(flag = new Array[BitSet](1)),"unkown","/","unknown", new Properties(),"unknown",
      DateTime.now(),"unknown",DateTime.now(),0,0,"/",null)
  }

  def parseXML(element: Element): Unit = {

  }

  sealed abstract class ComponentType(val id: Int, val bitIndex: Int, val flag: Array[BitSet]){


  }

  case class Unknown(override val id: Int = 0, override val bitIndex: Int = 0, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Folder(override val id: Int = 1, override val bitIndex: Int = 1, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class RealFolder(override val id: Int = 2, override val bitIndex: Int = 3, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Document(override val id: Int = 3, override val bitIndex: Int = 2, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Data(override val id: Int = 4, override val bitIndex: Int = 4, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Column(override val id: Int = 5, override val bitIndex: Int = 8, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Table(override val id: Int = 6, override val bitIndex: Int = 16, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Query(override val id: Int = 7, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class LogicalModel(override val id: Int = 8, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class DataSourceFolder(override val id: Int = 9, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class View(override val id: Int = 10, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Physical(override val id: Int = 11, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class PhysicalFolder(override val id: Int = 12, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class PhysicalTable(override val id: Int = 13, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class PhysicalColumn(override val id: Int = 14, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Module(override val id: Int = 15, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Shape(override val id: Int = 16, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class RepositoryFolder(override val id: Int = 17, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Snapshot(override val id: Int = 18, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Bookmark(override val id: Int = 19, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Variable(override val id: Int = 20, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class DataSource(override val id: Int = 21, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Bean(override val id: Int = 22, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class ParameterDocument(override val id: Int = 23, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class MetaTemplate(override val id: Int = 24, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class TableStye(override val id: Int = 25, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Script(override val id: Int = 26, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class ReportPart(override val id: Int = 27, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class BeanFolder(override val id: Int = 28, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class ParameterDocumentFolder(override val id: Int = 29, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class MetaTemplateFolder(override val id: Int = 30, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class TableStyleFolder(override val id: Int = 31, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class ScriptFolder(override val id: Int = 32, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class DataModel(override val id: Int = 33, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Partition(override val id: Int = 34, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class ModelExtension(override val id: Int = 35, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class PartitionExtension(override val id: Int = 36, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class LogicalModelExtension(override val id: Int = 37, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  /* VPM */
  case class PrivateModel(override val id: Int = 38, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class QueryFolder(override val id: Int = 39, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class LocalQueryFolder(override val id: Int = 40, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class ReportDocumentFolder(override val id: Int = 41, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class EmbeddedFolder(override val id: Int = 42, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Reportlet(override val id: Int = 43, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class Domain(override val id: Int = 44, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class RelationalModel(override val id: Int = 45, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class ScheduledActivity(override val id: Int = 46, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

  case class ScheduledActivityFolder(override val id: Int = 47, override val bitIndex: Int = 32, override val flag: Array[BitSet])
    extends ComponentType(id,bitIndex,flag) {
  }

}