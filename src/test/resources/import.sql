-- Excluir a tabela atendimento
DROP TABLE IF EXISTS atendimento;

-- Excluir a tabela cliente
DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(255),
    datanascimento DATE,
    email VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE atendimento (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    datahora TIMESTAMP NOT NULL, -- Nome da coluna correto
    tipoatendimento VARCHAR(50),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);



INSERT INTO cliente (cpf, datanascimento, email, name) VALUES
('123.456.789-00', '1985-05-15', 'joao.silva@example.com', 'João Silva'),
('987.654.321-99', '1990-09-25', 'maria.oliveira@example.com', 'Maria Oliveira');


-- Inserir dados fictícios na tabela atendimento
INSERT INTO atendimento (cliente_id, datahora, tipoatendimento) VALUES
(1, '2024-08-08T10:00:00', 1),
(2, '2024-08-08T11:00:00', 0);
