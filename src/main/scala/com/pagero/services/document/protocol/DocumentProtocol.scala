package com.pagero.services.document.protocol

import spray.json.DefaultJsonProtocol

case class Document(id: Option[Int], name: String, docType: String, date: String, party: PartyInfo)

case class DocumentResponse(meta: Meta, objects: List[Document])

object DocumentProtocol extends DefaultJsonProtocol {
  implicit val MetaFormat = jsonFormat(Meta, "limit", "next", "offset", "previous", "total_count")
  implicit val PartyInfoFormat = jsonFormat(PartyInfo, "id", "name", "orgNo", "vatNo")
  implicit val UserFormat = jsonFormat(Document, "id", "name", "docType", "date", "partyInfo")
  implicit val UserResponseFormat = jsonFormat(DocumentResponse, "meta", "objects")
}
