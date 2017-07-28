-- keep ids
--  1. org_no
--  2. vat_no
--  3. ovt_no
--  4. iban_no
--  5. unit_no
--  6. department
CREATE TYPE party_id (
    name TEXT,
    value TEXT
);

CREATE TYPE address (
    country TEXT,
    country_code TEXT,
    post_code TEXT,
    province TEXT,
    reference TEXT,
    street TEXT,
    town TEXT
);

CREATE TYPE attachment (
    storage_id TEXT,
    name TEXT,
    created_date DATE,
    doc_hash TEXT,
    hash_algorithm TEXT,
    type TEXT,
    mime_type TEXT,
    primary_presentation BOOLEAN,
    legal_document BOOLEAN,
    distributed BOOLEAN,
    presentation BOOLEAN,
    overridden BOOLEAN
);

CREATE TABLE documents (
    auth_company_id TEXT,
    internal_interchange_id TEXT,
    document_identifier TEXT,
    document_date DATE,
    document_due_date DATE,
    create_time_message_service TIMESTAMP,
    create_time_document_service TIMESTAMP,
    currency TEXT,
    total_amount NUMERIC,
    total_net_amount NUMERIC,
    total_vat_amount NUMERIC,
    distribution TEXT,
    direction TEXT,
    send_type TEXT,
    document_type TEXT,
    source_media_type TEXT,
    out_bound_file_name TEXT,
    external_legal_doc BOOLEAN,
    agreement_no TEXT,
    payment_ocr TEXT,
    document_specifier_type_raw TEXT,
    buyer_order_no TEXT,
    seller_order_no TEXT,

    sender_intermediator_id TEXT,
    sending_service_provider_id TEXT,
    sender_erp_id TEXT,
    sender_name TEXT,
    sender_gs1_prefix_id TEXT,
    sender_ids SET<frozen <party_id>>,
    sender_address frozen <address>,

    receiver_intermediator_id TEXT,
    receiving_service_provider_id TEXT,
    receiver_erp_id TEXT,
    receiver_name TEXT,
    receiver_gs1_prefix_id TEXT,
    receiver_ids SET<frozen <party_id>>,
    receiver_address frozen <address>,

    seller_erp_id TEXT,
    seller_name TEXT,
    seller_gs1_prefix_id TEXT,
    seller_ids SET<frozen <party_id>>,
    seller_address frozen <address>,

    buyer_erp_id TEXT,
    buyer_name TEXT,
    buyer_gs1_prefix_id TEXT,
    buyer_ids SET<frozen <party_id>>,
    buyer_email TEXT,
    buyer_contact TEXT,
    buyer_phone_no TEXT,
    buyer_address frozen <address>,

    attachments SET<frozen <attachment>>,

    PRIMARY KEY(auth_company_id, internal_interchange_id)
);

CREATE TABLE attachments (
    internal_interchange_id TEXT,
    storage_id TEXT,
    name TEXT,
    created_date DATE,
    doc_hash TEXT,
    hash_algorithm TEXT,
    type TEXT,
    mime_type TEXT,
    primary_presentation BOOLEAN,
    legal_document BOOLEAN,
    distributed BOOLEAN,
    presentation BOOLEAN,
    overridden BOOLEAN

    PRIMARY KEY(internal_interchange_id, storage_id)
);

