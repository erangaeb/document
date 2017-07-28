package com.pagero.services.document.protocol

case class Address(country: String, countryCode: String, postCode: String, province: String, reference: String, street: String, town: String)

case class SenderParty(erpId: String, name: String, gs1Prefix: String, orgNo: String, vatNo: String, iBanNo: String, unitNo: String, department: String, address: Address)

case class ReceiverParty(erpId: String, name: String, gs1Prefix: String, orgNo: String, vatNo: String, iBanNo: String, unitNo: String, department: String, address: Address)

case class SellerParty(erpId: String, name: String, gs1Prefix: String, orgNo: String, vatNo: String, iBanNo: String, unitNo: String, department: String, address: Address)

case class BuyerParty(erpId: String, name: String, gs1Prefix: String, orgNo: String, vatNo: String, iBanNo: String, unitNo: String, department: String, address: Address)

