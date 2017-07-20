package com.pagero.services.document.comp

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.QueryBuilder._
import com.pagero.services.document.actor.RestRequestHandlerActor.Criteria
import com.pagero.services.document.protocol.{Document, PartyInfo}
import com.pagero.services.document.util.CqlBuilder

import scala.collection.JavaConverters._

trait DocumentDbCompImpl extends DocumentDbComp {

  this: CassandraClusterComp =>

  val documentDb = new DocumentDbImpl

  class DocumentDbImpl extends DocumentDb {
    def getDocument(id: Int): Option[Document] = {
      // select query
      val selectStmt = select().all()
        .from("documents")
        .where(QueryBuilder.eq("id", id))
        .limit(1)

      val resultSet = session.execute(selectStmt)
      val row = resultSet.one()

      if (row != null)
        Option(Document(
          Option(id),
          row.getString("name"),
          row.getString("docType"),
          row.getString("date"),
          PartyInfo(
            None,
            row.getString("partyName"),
            row.getString("orgNo"),
            row.getString("vatNo")
          )
        ))
      else None
    }

    def getDocuments(criteria: Criteria): List[Document] = {
      //val selectStmt = select().all().from("documents")
      val selectStmt = CqlBuilder.buildSearchQuery(criteria)

      val resultSet = session.execute(selectStmt)
      resultSet.all().asScala.map(r =>
        Document(
          Option(r.getInt("id")),
          r.getString("name"),
          r.getString("docType"),
          r.getString("date"),
          PartyInfo(
            None,
            r.getString("partyName"),
            r.getString("orgNo"),
            r.getString("vatNo")
          )
        )
      ).toList
    }

    def createDocument(document: Document): Unit = {
      // insert query
      val statement = QueryBuilder.insertInto("documents")
        .value("id", document.id.get)
        .value("name", document.name)
        .value("docType", document.docType)
        .value("date", document.date)
        .value("partyName", document.party.name)
        .value("orgNo", document.party.orgNo)
        .value("vatNo", document.party.vatNo)

      session.execute(statement)
    }

    def updateDocument(id: Int, document: Document): Unit = {

    }
  }

}
