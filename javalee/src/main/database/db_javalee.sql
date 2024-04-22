CREATE DATABASE db_javalee;

CREATE SCHEMA db_javalee;

CREATE TABLE db_javalee.unidade_medida (
    id_unidade_medida SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    descricao VARCHAR(255) 
);

CREATE TABLE db_javalee.metrica(
	id_metrica SERIAL PRIMARY KEY,
	nome VARCHAR(255),
    minimo_risco DECIMAL(15, 10),
    maximo_risco DECIMAL(15, 10),
    id_unidade_medida INTEGER,
    
    FOREIGN KEY(id_unidade_medida) REFERENCES db_javalee.unidade_medida(id_unidade_medida)
);

CREATE TABLE db_javalee.cidade(
	id_cidade SERIAL PRIMARY KEY,
	sigla_cidade VARCHAR(255) NOT NULL,
    nome_cidade VARCHAR(255)
);

CREATE TABLE db_javalee.estacao(
	id_estacao SERIAL PRIMARY KEY,
	id_cidade INT,
    codigo VARCHAR(255),
    
    FOREIGN KEY(id_cidade) REFERENCES db_javalee.cidade(id_cidade)
);

CREATE TABLE db_javalee.registro(
	id_registro SERIAL PRIMARY KEY,
    id_metrica INT NOT NULL,
    id_estacao INT NOT NULL,
    valor DECIMAL(15, 10) NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    
    FOREIGN KEY(id_metrica) REFERENCES db_javalee.metrica(id_metrica),
    FOREIGN KEY(id_estacao) REFERENCES db_javalee.estacao(id_estacao)
);