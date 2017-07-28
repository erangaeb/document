package com.pagero.services.document.protocol

case class DocumentDateTime(documentDate: String, dueDate: String, messageServiceCreateTime: String, documentServiceCreateTime: String)

case class Doc(authCompanyId: String, internalInterchangeId: String, documentIdentifier: String, currency: String,
               totalAmount: String, netAmount: String, vatAmount: String, distribution: String, direction: String,
               sendType: String, documentType: String, sourceMediaType: String, outBoundFileName: String,
               externalLegalDoc: Boolean, agreementNo: String, paymentOct: String, documentSpecificTypeRaw: String,
               buyerOrderNo: String, sellerOrderNo: String)


