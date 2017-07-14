package com.pagero.services.document.protocol

import spray.json.DefaultJsonProtocol

case class Meta(limit: Int, next: Option[String], offset: Int, previous: Option[String], total_count: Int)

case class DocumentResponse(meta: Meta, objects: List[Document])

object DocumentResponseProtocol extends DefaultJsonProtocol {
  implicit val metaFormat = jsonFormat(Meta, "limit", "next", "offset", "previous", "total_count")
  implicit val partyInfoFormat = jsonFormat(PartyInfo, "id", "name", "orgNo", "vatNo")
  implicit val userFormat = jsonFormat(Document, "id", "name", "docType", "date", "partyInfo")
  implicit val userResponseFormat = jsonFormat(DocumentResponse, "meta", "objects")
}
