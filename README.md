# Document service

Service to keep documents with tow years of storage. Using cassandara as
storage backend. Lucen plugin using to full text search with cassandra. 

Document REST api builds against this service. 


# REST API usage

## API end points
```
dev.localhost:8080/api/v1/documents
dev.localhost:8080/api/v1/documents/1
dev.localhost:8080/api/v1/documents?name=eranga
dev.localhost:8080/api/v1/documents?name=eraga&orgNo=6881
```

## create document
```
# http POST
curl \
    -u john:123 \
    -H "Content-Type: application/json" \
    -H 'Authorization:Basic john:123' \
    -X POST http://localhost:8080/api/v1/documents \
    -d '{
        "name": "telia",
        "id": 3,
        "date": "2017/08/13",
        "docType": "INVOICE",
        "partyInfo": {
            "id": 2,
            "name": "eranga",
            "orgNo": "688812",
            "vatNo": "231122"
        }
    }'

# without auth header
curl \
    -u john:123 \
    -H "Content-Type: application/json" \
    -X POST http://localhost:8080/api/v1/documents \
    -d '{
        "name": "telia",
        "id": 3,
        "date": "2017/08/13",
        "docType": "INVOICE",
        "partyInfo": {
            "id": 2,
            "name": "eranga",
            "orgNo": "688812",
            "vatNo": "231122"
        }
    }'
```
