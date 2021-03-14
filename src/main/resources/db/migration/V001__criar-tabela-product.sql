-- CRIAÇÃO DO SCRIPT DE VERSIONAMENTO DA TABELA 'PRODUCT'

CREATE TABLE IF NOT EXISTS product(
id_cliente UUID not null,
name varchar(50) not null,
description varchar(50) null,
price decimal(19,2) not null default 0,
primary key (id_cliente)
);

