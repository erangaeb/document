package com.pagero.services.document.comp

import com.pagero.services.document.actor.RestRequestHandlerActor.Criteria
import com.pagero.services.document.protocol.Document

trait DocumentDbCompImpl extends DocumentDbComp {

  this: CassandraClusterComp =>

  val documentDb = new DocumentDbImpl

  class DocumentDbImpl extends DocumentDb {
    def getDocument(id: Int): Option[Document] = {
      None
    }

    def getDocuments(criteria: Criteria): List[Document] = {
      List()
    }

    def createDocument(document: Document): Unit = {

    }

    def updateDocument(id: Int, document: Document): Unit = {

    }
  }

}
