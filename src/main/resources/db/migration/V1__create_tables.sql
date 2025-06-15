DROP TABLE IF EXISTS unidade_endereco CASCADE;
DROP TABLE IF EXISTS pessoa_endereco CASCADE;
DROP TABLE IF EXISTS lotacao CASCADE;
DROP TABLE IF EXISTS servidor_efetivo CASCADE;
DROP TABLE IF EXISTS servidor_temporario CASCADE;
DROP TABLE IF EXISTS foto_pessoa CASCADE;
DROP TABLE IF EXISTS endereco CASCADE;
DROP TABLE IF EXISTS unidade CASCADE;
DROP TABLE IF EXISTS pessoa CASCADE;
DROP TABLE IF EXISTS cidade CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE cidade
(
    cid_id   bigserial PRIMARY KEY,
    cid_nome VARCHAR(200),
    cid_uf   VARCHAR(2)
);

CREATE TABLE endereco
(
    end_id              bigserial PRIMARY KEY,
    end_tipo_logradouro VARCHAR(200),
    end_logradouro      VARCHAR(200),
    end_numero          int8,
    end_bairro          VARCHAR(100),
    cid_id              int8 REFERENCES cidade (cid_id) ON DELETE CASCADE
);

CREATE TABLE pessoa
(
    pes_id              bigserial PRIMARY KEY,
    pes_nome            VARCHAR(200) NOT NULL,
    pes_data_nascimento DATE,
    pes_sexo            VARCHAR(9),
    pes_mae             VARCHAR(200),
    pes_pai             VARCHAR(200)
);

CREATE TABLE unidade
(
    unid_id    bigserial PRIMARY KEY,
    unid_nome  VARCHAR(200) NOT NULL,
    unid_sigla VARCHAR(20)
);


CREATE TABLE pessoa_endereco
(

    pes_id int8 REFERENCES pessoa (pes_id) ON DELETE CASCADE,
    end_id int8 REFERENCES endereco (end_id) ON DELETE CASCADE,
    PRIMARY KEY (pes_id, end_id)
);

CREATE TABLE unidade_endereco
(
    unid_id int8 REFERENCES unidade (unid_id) ON DELETE CASCADE,
    end_id  int8 REFERENCES endereco (end_id) ON DELETE CASCADE,
    PRIMARY KEY (unid_id, end_id)
);

CREATE TABLE foto_pessoa
(
    fp_id     bigserial PRIMARY KEY,
    pes_id    int8 REFERENCES pessoa (pes_id) ON DELETE CASCADE,
    fp_data   DATE,
    fp_bucket VARCHAR(200),
    fp_hash   VARCHAR(200)
);

CREATE TABLE lotacao
(
    lot_id           bigserial PRIMARY KEY,
    pes_id           int8 REFERENCES pessoa (pes_id) ON DELETE CASCADE,
    unid_id          int8 REFERENCES unidade (unid_id) ON DELETE CASCADE,
    lot_data_lotacao DATE,
    lot_data_remocao DATE,
    lot_portaria     VARCHAR(200)
);

CREATE TABLE servidor_temporario
(
    st_id            bigserial PRIMARY KEY,
    pes_id           int8 REFERENCES pessoa (pes_id) ON DELETE CASCADE,
    st_data_admissao DATE,
    st_data_demissao DATE
);

CREATE TABLE servidor_efetivo
(
    se_id        bigserial PRIMARY KEY,
    pes_id       int8 REFERENCES pessoa (pes_id) NOT NULL UNIQUE,
    se_matricula VARCHAR(20)                     NOT NULL UNIQUE
);

CREATE TABLE users (
                       id bigserial PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       ativo BOOLEAN NOT NULL DEFAULT TRUE
);
