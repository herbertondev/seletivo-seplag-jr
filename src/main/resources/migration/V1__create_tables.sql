
CREATE TABLE pessoa (
    pes_id SERIAL PRIMARY KEY,
    pes_nome VARCHAR(200) NOT NULL,
    pes_data_nascimento DATE,
    pes_sexo VARCHAR(9),
    pes_mae VARCHAR(200),
    pes_pai VARCHAR(200)
);

CREATE TABLE unidade (
    unid_id SERIAL PRIMARY KEY,
    unid_nome VARCHAR(200) NOT NULL,
    unid_sigla VARCHAR(20)
);

CREATE TABLE endereco (

    end_id SERIAL PRIMARY KEY,
    end_tipo_logradouro VARCHAR(50),
    end_logradouro VARCHAR(200),
    end_numero INT,
    end_bairro VARCHAR(100),
    cid_id INT
);

CREATE TABLE cidade (
    cid_id SERIAL PRIMARY KEY,
    cid_nome VARCHAR(200),
    cid_uf CHAR(2)
);

CREATE TABLE foto_pessoa (
    fp_id SERIAL PRIMARY KEY,
    pes_id INT REFERENCES pessoa(pes_id) ON DELETE CASCADE,
    fp_data DATE,
    fp_bucket VARCHAR(50),
    fp_hash VARCHAR(50)
);

CREATE TABLE lotacao (
    lot_id SERIAL PRIMARY KEY,
    pes_id INT REFERENCES pessoa(pes_id) ON DELETE CASCADE,
    unid_id INT REFERENCES unidade(unid_id) ON DELETE CASCADE,
    lot_data_latacao DATE,
    lot_data_remocao DATE,
    lot_portaria VARCHAR(200)
);

CREATE TABLE servidor_temporario (
    st_data_admissao date NULL,
    st_data_demissao date NULL,
    st_pes_id INT REFERENCES pessoa(pes_id) ON DELETE CASCADE,
    st_id int8 PRIMARY KEY
);

CREATE TABLE servidor_efetivo (
    se_id int8 PRIMARY KEY,
    se_pes_id INT REFERENCES pessoa(pes_id) NOT NULL UNIQUE,
    se_matricula VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE pessoa_endereco (
    pes_id INT REFERENCES pessoa(pes_id) ON DELETE CASCADE,
    end_id INT REFERENCES endereco(end_id) ON DELETE CASCADE,
    PRIMARY KEY (pes_id, end_id)
);

CREATE TABLE unidade_endereco (
    unid_id INT REFERENCES unidade(unid_id) ON DELETE CASCADE,
    end_id INT REFERENCES endereco(end_id) ON DELETE CASCADE,
    PRIMARY KEY (unid_id, end_id)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);
