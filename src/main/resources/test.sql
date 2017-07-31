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
)

CREATE TYPE address (
    country TEXT,
    country_code TEXT,
    town TEXT
)

CREATE TYPE attachment (
    storage_id TEXT,
    name TEXT
)

CREATE TABLE documents (
    auth_company_id TEXT,
    internal_interchange_id TEXT,
    document_identifier TEXT,
    document_type TEXT,
    distribution TEXT,
    direction TEXT,
    send_type TEXT,
    sender_erp_id TEXT,
    sender_ids SET<frozen <party_id>>,
    sender_address frozen <address>,
    receiver_erp_id TEXT,
    receiver_ids SET<frozen <party_id>>,
    receiver_address frozen <address>,
    attachments SET<frozen <attachment>>,

    PRIMARY KEY(auth_company_id, internal_interchange_id)
)

INSERT INTO documents
    (
        auth_company_id,
        internal_interchange_id,
        document_identifier,
        sender_ids,
        sender_address,
        attachments
    )
VALUES
    (
        '34332',
        '45444444',
        'INVOICE',
        {
            {name: 'orgNo', value: '5444'},
            {name: 'vatNo', value: '89999'}
        },
        {
            country: 'sweden',
            country_code: 'SE',
            town: 'malmo'
        },
        {
            {storage_id: '9000001', name: 'primary-te'},
            {storage_id: '7000001', name: 'tel-att'}
        }
    )
