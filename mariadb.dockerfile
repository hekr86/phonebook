FROM mariadb:10.7
ADD sql/init.sql /docker-entrypoint-initdb.d/ddl.sql