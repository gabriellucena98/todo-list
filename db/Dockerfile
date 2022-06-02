FROM postgres:14.2

ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD docker
ENV POSTGRES_DB todo-list

EXPOSE 5432:5432

ADD schema.sql /docker-entrypoint-initdb.d/









