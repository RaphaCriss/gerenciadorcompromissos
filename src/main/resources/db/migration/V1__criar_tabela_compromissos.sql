CREATE TABLE tb_compromissos (
    compromisso_id CHAR(36) PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    data_hora DATETIME NOT NULL,
    cep VARCHAR(8) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    alerta_dias_antes INT NOT NULL CHECK (alerta_dias_antes >= 0),
    id_telegram BIGINT NOT NULL,
    criado_em DATETIME,
    atualizado_em DATETIME,
    creation_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
