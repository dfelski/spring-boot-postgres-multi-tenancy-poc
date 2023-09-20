# Multi-tenancy using Postgres Row Level Security

Let's try to implement multi-tenancy using Postgres Row Level Security in a simple Spring Boot application.

# Setup
Just one table, but two different users. One for schema creation using liquibase and one for the application itself.

- database: test
- just one table: test_data
- admin user: admin/admin (see docker-compose.yml) 
- application user: appuser/appuser (see docker-entrypoint-initdb.d/init-test-db.sh) 

Roles 'pg_read_all_data' and 'pg_write_all_data' are explicitly granted to appuser to be able to read and write all data.
Liquibase uses admin user for table creation, application uses appuser to access the data.

In a real implementation we would of course use something like JWT tokens to determine the current user and its organization/group/tenant 
but here we'll just simulate the tenant using a HTTP header called "tenant".

# Communication via HTTP
## Create Test Data
We'll create two simple entries, one for tenant "org1" and one for "org2"
```
curl --request POST \
--url http://localhost:8080/data \
--header 'tenant: org1'
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data '{"value" : "a1"}'
```

```
curl --request POST \
--url http://localhost:8080/data \
--header 'tenant: org2'
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data '{"value" : "a2"}'
```

## List Test Data
The response of our collection resource should be now different depending on the data passed in the "tenant" header 

```
curl --request GET --header 'tenant: org1' --url http://localhost:8080/data
```

## Retrieve Test Data
Same should also work if we try to access one dedicated resource directly by id.
```
curl --request GET --header 'tenant: org1' --url http://localhost:8080/data/<UUID>
```

