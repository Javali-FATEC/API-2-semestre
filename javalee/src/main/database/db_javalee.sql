CREATE DATABASE db_javali;

CREATE SCHEMA db_javalee;

CREATE TABLE unidade_medida (
    id_unidade_medida SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    descricao VARCHAR(255) 
);

CREATE TABLE metrica(
	id_metrica SERIAL PRIMARY KEY,
	nome VARCHAR(255),
    minimo_risco DECIMAL(15, 10),
    maximo_risco DECIMAL(15, 10),
    id_unidade_medida INTEGER,
    
    FOREIGN KEY(id_unidade_medida) REFERENCES unidade_medida(id_unidade_medida)
);

CREATE TABLE cidade(
	id_cidade SERIAL PRIMARY KEY,
	sigla_cidade VARCHAR(255) NOT NULL,
    nome_cidade VARCHAR(255)
);

CREATE TABLE estacao(
	id_estacao SERIAL PRIMARY KEY,
	id_cidade INT,
    codigo VARCHAR(255),
    
    FOREIGN KEY(id_cidade) REFERENCES cidade(id_cidade)
);

CREATE TABLE registro(
	id_registro SERIAL PRIMARY KEY,
    id_metrica INT NOT NULL,
    id_estacao INT NOT NULL,
    valor DECIMAL(15, 10) NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    
    FOREIGN KEY(id_metrica) REFERENCES metrica(id_metrica),
    FOREIGN KEY(id_estacao) REFERENCES estacao(id_estacao)
);


INSERT INTO db_javalee.unidade_medida (nome, descricao) VALUES ('ºC', 'graus Celsius');
INSERT INTO db_javalee.unidade_medida (nome, descricao) VALUES ('%', 'porcentagem');
INSERT INTO db_javalee.unidade_medida (nome, descricao) VALUES ('hPa', 'hectopascais');
INSERT INTO db_javalee.unidade_medida (nome, descricao) VALUES ('m/s', 'metros por segundo');
INSERT INTO db_javalee.unidade_medida (nome, descricao) VALUES ('Decimos', 'unidade não padrão para nebulosidade');
INSERT INTO db_javalee.unidade_medida (nome, descricao) VALUES ('h', 'horas');
INSERT INTO db_javalee.unidade_medida (nome, descricao) VALUES ('mm', 'milímetros');
INSERT INTO db_javalee.unidade_medida (nome, descricao) VALUES ('KJ/m²', 'Quilo joules por metro quadrado');

INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Chuva', NULL, NULL, 7);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Direção do Vento', NULL, NULL, 4);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Insolação', NULL, NULL, 6);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Nebulosidade', NULL, NULL, 5);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Ponto de Orvalho Instantâneo', NULL, NULL, 1);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Ponto de Orvalho Máximo', NULL, NULL, 1);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Ponto de Orvalho Mínimo', NULL, NULL, 1);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Pressão', NULL, NULL, 3);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Pressão Máxima', NULL, NULL, 3);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Pressão Mínima', NULL, NULL, 3);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Rajada de Vento', NULL, NULL, 4);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Radiação Solar', NULL, NULL, 8);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Temperatura', NULL, NULL, 1);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Temperatura Máxima', NULL, NULL, 1);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Temperatura Mínima', NULL, NULL, 1);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Umidade', NULL, NULL, 2);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Umidade Máxima', NULL, NULL, 2);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Umidade Mínima', NULL, NULL, 2);
INSERT INTO db_javalee.metrica (nome, minimo_risco, maximo_risco, id_unidade_medida) VALUES ('Velocidade do Vento', NULL, NULL, 4);