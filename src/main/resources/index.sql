CREATE CUSTOM INDEX documents_index ON documents ()
USING 'com.stratio.cassandra.lucene.Index'
WITH OPTIONS = {
   'refresh_seconds': '1',
   'schema': '{
      fields: {
         auth_company_id: {type: "string"},
         internal_interchange_id: {type: "string"},
         document_identifier: {type: "string"},
         distribution: {type: "string"},
         direction: {type: "string"},
         send_type: {type: "string"},
         document_type: {type: "string"}
      }
   }'
}
