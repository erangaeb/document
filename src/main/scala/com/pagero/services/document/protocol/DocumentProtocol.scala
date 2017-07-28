package com.pagero.services.document.protocol

import spray.json.DefaultJsonProtocol

case class PartyInfo(id: Option[Int], name: String, orgNo: String, vatNo: String)

object PartyInfoProtocol extends DefaultJsonProtocol {
  implicit val partyInfoFormat = jsonFormat(PartyInfo, "id", "name", "orgNo", "vatNo")
  //  implicit object PartyInfoJsonFormat extends RootJsonFormat[PartyInfo] {
  //    def write(p: PartyInfo) = JsObject(
  //      "orgNo" -> JsString(p.orgNo),
  //      "vatNo" -> JsString(p.vatNo)
  //    )
  //
  //    def read(value: JsValue): PartyInfo = {
  //      value.asJsObject.getFields("name", "orgNo", "vatNo") match {
  //        case Seq(JsString(name), JsString(orgNo), JsString(vatNo)) =>
  //          PartyInfo(None, name, orgNo, vatNo)
  //        case _ => throw DeserializationException("Party expected")
  //      }
  //    }
  //  }
}

case class Document(id: Option[Int], name: String, docType: String, date: String, party: PartyInfo)

object DocumentProtocol extends DefaultJsonProtocol {

  import PartyInfoProtocol._

  implicit val documentFormat = jsonFormat(Document, "id", "name", "docType", "date", "partyInfo")

}

