package com.pagero.services.document.protocol

import spray.json.DefaultJsonProtocol

case class PartyInfo(id: Option[Int], name: String, orgNo: String, vatNo: String)

case class Document(id: Option[Int], name: String, docType: String, date: String, party: PartyInfo)

object DocumentProtocol extends DefaultJsonProtocol {
  implicit val partyInfoFormat = jsonFormat(PartyInfo, "id", "name", "orgNo", "vatNo")
  implicit val documentFormat = jsonFormat(Document, "id", "name", "docType", "date", "partyInfo")
}

