-- V2__add_password_column.sql

-- Adicionar coluna como nullable primeiro
ALTER TABLE VITS_ORC_USUARIO ADD (vits_senha_usuario VARCHAR2(500));

-- Atualizar com valor padrão temporário
UPDATE VITS_ORC_USUARIO SET vits_senha_usuario = 'temporary_password' WHERE vits_senha_usuario IS NULL;

-- Agora tornar NOT NULL
ALTER TABLE VITS_ORC_USUARIO MODIFY vits_senha_usuario VARCHAR2(500) NOT NULL;
