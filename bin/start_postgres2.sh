#!/usr/bin/env bash

set -euo pipefail
which psql > /dev/null || (echoerr "Please ensure that postgres client is in your PATH" && exit 1)

mkdir -p $HOME/docker/volumes/postgresProduct
rm -rf $HOME/docker/volumes/postgresProduct/data

docker run --rm --name pg-docker -e POSTGRES_PASSWORD=Sri1_sai -e POSTGRES_DB=dev -d -p 5432:5432 -v $HOME/docker/volumes/postgresProduct:/var/lib/postgresql postgres
sleep 3
export PGPASSWORD=Sri1_sai
psql -U postgres -d dev -h localhost -f productSchema.sql
psql -U postgres -d dev -h localhost -f productData.sql