-- Inserir dados na tabela cidade
INSERT INTO cidade (cid_nome, cid_uf)
VALUES ('São Paulo', 'SP'),
       ('Rio de Janeiro', 'RJ'),
       ('Belo Horizonte', 'MG');

-- Inserir dados na tabela pessoa
INSERT INTO pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai)
VALUES ('João Silva', '1980-01-01', 'Masculino', 'Maria Silva', 'José Silva'),
       ('Ana Souza', '1990-05-10', 'Feminino', 'Elisa Souza', 'Carlos Souza');

-- Inserir dados na tabela unidade
INSERT INTO unidade (unid_nome, unid_sigla)
VALUES ('Administração', 'ADM'),
       ('RECURSOS HUMANOS', 'RH'),
       ('TI', 'TI');

-- Inserir dados na tabela endereco
INSERT INTO endereco (end_tipo_logradouro, end_logradouro, end_numero, end_bairro, cid_id)
VALUES ('Rua', 'Avenida Paulista', 1000, 'Bela Vista', 1), -- São Paulo
       ('Avenida', 'Avenida Atlântica', 200, 'Copacabana', 2);
-- Rio de Janeiro

-- Inserir dados na tabela foto_pessoa
INSERT INTO foto_pessoa (pes_id, fp_data, fp_bucket, fp_hash)
VALUES (1, '2025-01-01', 'foto_bucket', 'hash_123456'),
       (2, '2025-01-02', 'foto_bucket', 'hash_789012');

-- Inserir dados na tabela lotacao
INSERT INTO lotacao (pes_id, unid_id, lot_data_lotacao, lot_data_remocao, lot_portaria)
VALUES (1, 1, '2025-01-01', NULL, 'Portaria 001'),
       (2, 2, '2025-01-01', NULL, 'Portaria 002');

-- Inserir dados na tabela servidor_temporario
INSERT INTO servidor_temporario (st_id, st_data_admissao, st_data_demissao, pes_id)
VALUES (1, '2025-01-01', NULL, 1),
       (2, '2025-02-01', NULL, 2);

-- Inserir dados na tabela servidor_efetivo
INSERT INTO servidor_efetivo (se_id, pes_id, se_matricula)
VALUES (1, 1, '12345'),
       (2, 2, '67890');

-- Inserir dados na tabela pessoa_endereco
INSERT INTO pessoa_endereco (pes_id, end_id)
VALUES (1, 1),
       (2, 2);

-- Inserir dados na tabela unidade_endereco
INSERT INTO unidade_endereco (unid_id, end_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO users (username, password, role, ativo)
VALUES('admin', 'admin', 'ADMIN', true),
      ('joaosilva', 'senha123', 'USER', true);
