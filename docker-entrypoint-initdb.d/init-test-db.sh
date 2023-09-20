#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  CREATE USER appuser WITH PASSWORD 'appuser';
  GRANT pg_read_all_data TO appuser;
  GRANT pg_write_all_data TO appuser;
EOSQL