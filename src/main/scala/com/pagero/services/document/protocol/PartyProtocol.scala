package com.pagero.services.document.protocol

case class Address(country: String, countryCode: String, postCode: String, province: String, reference: String, street: String, town: String)

// keep ids
//  1. org_no
//  2. vat_no
//  3. ovt_no
//  4. iban_no
//  5. unit_no
//  6. department
case class PartyId(name: String, value: String)

case class SenderParty(erpId: String, name: String, partyIds: List[PartyId], address: Address)

case class ReceiverParty(erpId: String, name: String, partyIds: List[PartyId], address: Address)

case class SellerParty(erpId: String, name: String, partyIds: List[PartyId], address: Address)

case class BuyerParty(erpId: String, name: String, partyIds: List[PartyId], address: Address)

