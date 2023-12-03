FROM mysql:latest


ENV MYSQL_ROOT_PASSWORD=asdasd
ENV MYSQL_DATABASE=Hangman
ENV MYSQL_USER=HangmanDefault
ENV MYSQL_PASSWORD=asdasd


COPY schema.sql /docker-entrypoint-schemadb.d/
COPY schema.sql /docker-entrypoint-initdb.d/