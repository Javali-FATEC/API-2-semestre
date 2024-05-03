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

INSERT INTO db_javalee.cidade (sigla_cidade, nome_cidade) VALUES 
('NY', 'Nova Iorque'),
('LA', 'Los Angeles'),
('LDN', 'Londres');

-- Inserts para a tabela 'db_javalee.estacao'
INSERT INTO db_javalee.estacao (id_cidade, codigo) VALUES 
(1, 'NY001'),
(2, 'LA001'),
(3, 'LDN001');

-- Inserts para a tabela 'db_javalee.registro'
-- Exemplo de registros fictícios para a cidade de Nova Iorque (ID da cidade = 1)
INSERT INTO db_javalee.registro (id_metrica, id_estacao, valor, data_hora) VALUES 
(1, 1, 15.5, '2024-05-01 12:00:00'),
(2, 1, 70, '2024-05-01 12:00:00'),
(3, 1, 8, '2024-05-01 12:00:00'),
(4, 1, 50, '2024-05-01 12:00:00'),
(5, 1, 10, '2024-05-01 12:00:00'),
(6, 1, 10, '2024-05-01 06:00:00'),
(7, 1, 12, '2024-05-01 09:00:00'),
(8, 1, 1015.5, '2024-05-01 12:30:00'),
(9, 1, 1017.5, '2024-05-01 15:00:00'),
(10, 1, 1013.5, '2024-05-01 18:00:00'),
(11, 1, 20, '2024-05-01 21:00:00'),
(12, 1, 200, '2024-05-02 00:00:00'),
(13, 1, 15, '2024-05-02 03:00:00'),
(14, 1, 75, '2024-05-02 06:00:00'),
(15, 1, 65, '2024-05-02 09:00:00'),
(1, 3, 18, '2024-05-01 08:00:00'),
(2, 3, 72, '2024-05-01 11:00:00'),
(3, 3, 12, '2024-05-01 14:00:00'),
(4, 3, 60, '2024-05-01 17:00:00'),
(5, 3, 8, '2024-05-01 20:00:00'),
(6, 3, 10, '2024-05-01 23:00:00'),
(7, 3, 16, '2024-05-02 02:00:00'),
(8, 3, 1015.7, '2024-05-02 05:00:00'),
(9, 3, 1017.7, '2024-05-02 08:00:00'),
(10, 3, 1013.7, '2024-05-02 11:00:00');


