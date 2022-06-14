# Explicação dos comandos:

version: "3.1" versao do compose

services: setando os serviços

db: #nome do serviço
image: postgres:14.3-alpine #setando a imagem
container_name: nome do container
restart: always #definindo estratégia de restart
environment:
POSTGRES_PASSWORD: example #setando a senha
ports:
- "5432:5432" #expondo a porta padrão do postgres

* POSTGRES_USER é OPCIONAL. Se nao setar POSTGRES_USER será usado o padrão "postgres"
* POSTGRES_DB é OPCIONAL. Se nao setar POSTGRES_DB será usado o nome do POSTGRES_USER
* FONTE: https://hub.docker.com/_/postgres
* Versão alpine é a versão mais leve, por isso foi usado no projeto

#Referências:
* https://hub.docker.com/_/postgres
* https://alpinelinux.org/
* https://medium.com/swlh/alpine-slim-stretch-buster-jessie-bullseye-bookworm-what-ar
* https://www.youtube.com/watch?v=bQXFdQ7Dwyw
* https://renatogroffe.medium.com/postgresql-pgadmin-4-docker-compose-montando-rapidamente-um-ambiente-para-uso-55a2ab230b89
* https://nickjanetakis.com/blog/docker-tip-45-docker-compose-stop-vs-down#:~:text=The%20docker%2Dcompose%20stop%20command,to%20remove%20all%20volumes%20too.
