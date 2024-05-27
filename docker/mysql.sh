docker run -d \
--name fintech-mysql \
-e MYSQL_ROOT_PASSWORD="12" \
-e MYSQL_USER="root" \
-e MYSQL_PASSWORD="12" \
-e MYSQL_DATABASE="trend_report" \
-p 3306:3306 \
--network docker_fintech mysql:latest