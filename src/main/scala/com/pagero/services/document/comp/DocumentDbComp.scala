package com.pagero.services.document.comp

import com.pagero.services.document.actor.RestRequestHandlerActor.Criteria
import com.pagero.services.document.protocol.Document


trait DocumentDbComp {

  val documentDb: DocumentDb

  trait DocumentDb {

    def getDocument(id: Int): Option[Document]

    def getDocuments(criteria: Criteria): List[Document]

    def createDocument(document: Document)

    def updateDocument(id: Int, document: Document)

  }

}

