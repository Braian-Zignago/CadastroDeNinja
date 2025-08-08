--V2: Migrations para adicionar uma columna rank na tabela tb_cadastro

ALTER TABLE tb_cadastro
ADD COLUMN rank VARCHAR(255);