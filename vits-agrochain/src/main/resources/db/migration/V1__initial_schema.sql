-- V1__initial_schema.sql

-- Create sequence for user types
CREATE SEQUENCE SEQ_TIPO_USUARIO
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create sequence for users
CREATE SEQUENCE SEQ_USUARIO
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create sequence for countries
CREATE SEQUENCE SEQ_PAIS
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create sequence for states
CREATE SEQUENCE SEQ_ESTADO
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create sequence for municipalities
CREATE SEQUENCE SEQ_MUNICIPIO
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create sequence for addresses
CREATE SEQUENCE SEQ_ENDERECO
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create sequence for properties
CREATE SEQUENCE SEQ_PROPRIEDADE
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create sequence for culture categories
CREATE SEQUENCE SEQ_CATEGORIA_CULTURA
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create sequence for cultures
CREATE SEQUENCE SEQ_CULTURA
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create sequence for lots
CREATE SEQUENCE SEQ_LOTE
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create sequence for certification types
CREATE SEQUENCE SEQ_TIPO_CERTIFICACAO
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create sequence for certifications
CREATE SEQUENCE SEQ_CERTIFICACAO
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create sequence for contact types
CREATE SEQUENCE SEQ_TIPO_CONTATO
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create sequence for user contacts
CREATE SEQUENCE SEQ_CONTATO_USUARIO
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create sequence for companies
CREATE SEQUENCE SEQ_EMPRESA
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create sequence for company categories
CREATE SEQUENCE SEQ_CATEGORIA_EMPRESA
    START WITH 1
    INCREMENT BY 1
    NOCYCLE;

-- Create UserType table
CREATE TABLE VITS_ORC_TIPO_USUARIO (
    id_tipo_usuario NUMBER PRIMARY KEY,
    vits_usuario_descrição VARCHAR2(100) NOT NULL UNIQUE
);

-- Create Country table
CREATE TABLE VITS_ORC_PAIS (
    vits_cod_pais NUMBER PRIMARY KEY,
    vits_nome_pais VARCHAR2(100) NOT NULL UNIQUE
);

-- Create State table
CREATE TABLE VITS_ORC_ESTADO (
    vits_cod_estado NUMBER PRIMARY KEY,
    vits_nome_estado VARCHAR2(100) NOT NULL,
    vits_uf_estado CHAR(2) NOT NULL,
    vits_cod_pais NUMBER NOT NULL,
    FOREIGN KEY (vits_cod_pais) REFERENCES VITS_ORC_PAIS(vits_cod_pais)
);

-- Create Municipality table
CREATE TABLE VITS_ORC_MUNICIPIO (
    vits_cod_municipio NUMBER PRIMARY KEY,
    vits_nome_municipio VARCHAR2(100) NOT NULL,
    vits_cod_estado NUMBER NOT NULL,
    FOREIGN KEY (vits_cod_estado) REFERENCES VITS_ORC_ESTADO(vits_cod_estado)
);

-- Create Address table
CREATE TABLE VITS_ORC_ENDERECO (
    vits_cod_endereco NUMBER PRIMARY KEY,
    vits_rua_endereco VARCHAR2(200),
    vits_numero_endereco NUMBER,
    vits_complemento_endereco VARCHAR2(200),
    vits_bairro_endereco VARCHAR2(100),
    vits_cep_endereco VARCHAR2(10),
    vits_cod_municipio NUMBER NOT NULL,
    FOREIGN KEY (vits_cod_municipio) REFERENCES VITS_ORC_MUNICIPIO(vits_cod_municipio)
);

-- Create User table
CREATE TABLE VITS_ORC_USUARIO (
    vits_id_usuario NUMBER PRIMARY KEY,
    vits_nome_usuario VARCHAR2(200) NOT NULL,
    vits_email_usuario VARCHAR2(200) NOT NULL UNIQUE,
    vits_senha_usuario VARCHAR2(500) NOT NULL,
    vits_data_cadastro TIMESTAMP NOT NULL,
    id_tipo_usuario NUMBER NOT NULL,
    FOREIGN KEY (id_tipo_usuario) REFERENCES VITS_ORC_TIPO_USUARIO(id_tipo_usuario)
);

-- Create ContactType table
CREATE TABLE VITS_ORC_TIPO_CONTATO (
    vits_cod_tipo_contato NUMBER PRIMARY KEY,
    vits_descricao_tipo_contato VARCHAR2(100) NOT NULL
);

-- Create UserContact table
CREATE TABLE VITS_ORC_CONTATO_USUARIO (
    vits_cod_contato NUMBER PRIMARY KEY,
    vits_valor_contato VARCHAR2(200) NOT NULL,
    vits_id_usuario NUMBER NOT NULL,
    vits_cod_tipo_contato NUMBER NOT NULL,
    FOREIGN KEY (vits_id_usuario) REFERENCES VITS_ORC_USUARIO(vits_id_usuario),
    FOREIGN KEY (vits_cod_tipo_contato) REFERENCES VITS_ORC_TIPO_CONTATO(vits_cod_tipo_contato)
);

-- Create Property table
CREATE TABLE VITS_ORC_PROPRIEDADE (
    vits_cod_propriedade NUMBER PRIMARY KEY,
    vits_nome_propriedade VARCHAR2(200) NOT NULL,
    vits_area_propriedade DECIMAL(10, 2),
    vits_cod_endereco NUMBER NOT NULL,
    vits_id_usuario NUMBER NOT NULL,
    FOREIGN KEY (vits_cod_endereco) REFERENCES VITS_ORC_ENDERECO(vits_cod_endereco),
    FOREIGN KEY (vits_id_usuario) REFERENCES VITS_ORC_USUARIO(vits_id_usuario)
);

-- Create CultureCategory table
CREATE TABLE VITS_ORC_CATEGORIA_CULTURA (
    vits_cod_categoria_cultura NUMBER PRIMARY KEY,
    vits_nome_categoria_cultura VARCHAR2(100) NOT NULL UNIQUE
);

-- Create Culture table
CREATE TABLE VITS_ORC_CULTURA (
    vits_cod_cultura NUMBER PRIMARY KEY,
    vits_nome_cultura VARCHAR2(100) NOT NULL,
    vits_cod_categoria_cultura NUMBER NOT NULL,
    FOREIGN KEY (vits_cod_categoria_cultura) REFERENCES VITS_ORC_CATEGORIA_CULTURA(vits_cod_categoria_cultura)
);

-- Create Lot table
CREATE TABLE VITS_ORC_LOTE (
    vits_cod_lote NUMBER PRIMARY KEY,
    vits_identificacao_lote VARCHAR2(100) NOT NULL,
    vits_area_lote DECIMAL(10, 2),
    vits_data_plantio DATE,
    vits_data_colheita DATE,
    vits_cod_propriedade NUMBER NOT NULL,
    vits_cod_cultura NUMBER NOT NULL,
    FOREIGN KEY (vits_cod_propriedade) REFERENCES VITS_ORC_PROPRIEDADE(vits_cod_propriedade),
    FOREIGN KEY (vits_cod_cultura) REFERENCES VITS_ORC_CULTURA(vits_cod_cultura)
);

-- Create CertificationType table
CREATE TABLE VITS_ORC_TIPO_CERTIFICACAO (
    vits_cod_tipo_certificacao NUMBER PRIMARY KEY,
    vits_nome_certificacao VARCHAR2(100) NOT NULL UNIQUE,
    vits_descricao_certificacao VARCHAR2(500)
);

-- Create Certification table
CREATE TABLE VITS_ORC_CERTIFICACOES (
    vits_cod_certific NUMBER PRIMARY KEY,
    id_tipo_certificaçao NUMBER NOT NULL,
    FOREIGN KEY (id_tipo_certificaçao) REFERENCES VITS_ORC_TIPO_CERTIFICACAO(vits_cod_tipo_certificacao)
);

-- Create CompanyCategory table
CREATE TABLE VITS_ORC_CATEGORIA_EMPRESA (
    vits_cod_categoria_empresa NUMBER PRIMARY KEY,
    vits_nome_categoria_empresa VARCHAR2(100) NOT NULL UNIQUE
);

-- Create Company table
CREATE TABLE VITS_ORC_EMPRESA (
    vits_cod_empresa NUMBER PRIMARY KEY,
    vits_nome_empresa VARCHAR2(200) NOT NULL,
    vits_cnpj_empresa VARCHAR2(20) UNIQUE,
    vits_cod_categoria_empresa NUMBER NOT NULL,
    FOREIGN KEY (vits_cod_categoria_empresa) REFERENCES VITS_ORC_CATEGORIA_EMPRESA(vits_cod_categoria_empresa)
);

-- Create indexes for better performance
CREATE INDEX idx_usuario_email ON VITS_ORC_USUARIO(vits_email_usuario);
CREATE INDEX idx_usuario_tipo ON VITS_ORC_USUARIO(id_tipo_usuario);
CREATE INDEX idx_propriedade_usuario ON VITS_ORC_PROPRIEDADE(vits_id_usuario);
CREATE INDEX idx_lote_propriedade ON VITS_ORC_LOTE(vits_cod_propriedade);
CREATE INDEX idx_lote_cultura ON VITS_ORC_LOTE(vits_cod_cultura);
CREATE INDEX idx_contato_usuario ON VITS_ORC_CONTATO_USUARIO(vits_id_usuario);
CREATE INDEX idx_estado_pais ON VITS_ORC_ESTADO(vits_cod_pais);
CREATE INDEX idx_municipio_estado ON VITS_ORC_MUNICIPIO(vits_cod_estado);
CREATE INDEX idx_endereco_municipio ON VITS_ORC_ENDERECO(vits_cod_municipio);
CREATE INDEX idx_propriedade_endereco ON VITS_ORC_PROPRIEDADE(vits_cod_endereco);
