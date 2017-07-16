package com.pagero.services.document.comp

import com.pagero.services.document.actor.RestRequestHandlerActor.Criteria
import com.pagero.services.document.protocol.{Document, PartyInfo}

trait DocumentDbCompImpl extends DocumentDbComp {

  this: CassandraClusterComp =>

  val documentDb = new DocumentDbImpl

  class DocumentDbImpl extends DocumentDb {
    def getDocument(id: Int): Option[Document] = {
      val doc = Document(Option(3), "telia", "INVOICE", "2017/08/13", PartyInfo(Option(2), "eranga", "688812", "231122"))
      Option(doc)
    }

    def getDocuments(criteria: Criteria): List[Document] = {
      val doc1 = Document(Option(3), "telia", "INVOICE", "2017/08/13", PartyInfo(Option(2), "eranga", "688812", "231122"))
      val doc2 = Document(Option(4), "conviq", "INVOICE", "2017/08/24", PartyInfo(Option(3), "badara", "688812", "231122"))
      List(doc1, doc2)
    }

    def createDocument(document: Document): Unit = {

    }

    def updateDocument(id: Int, document: Document): Unit = {

    }
  }

}
