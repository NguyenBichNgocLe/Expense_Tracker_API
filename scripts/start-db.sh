#!/bin/bash

docker run --name mysql -e MYSQL_DATABASE=expense-tracker -e MYSQL_ROOT_PASSWORD='Password0.' -p 3306:3306 -d mysql