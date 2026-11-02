-- =====================================================================
-- MecâniQA - OAT 3 - Script do banco de dados (DDL)
-- Banco: mecaniqa (MySQL 8)
-- Ordem: tabelas sem dependência primeiro, tabelas com chave estrangeira depois
-- =====================================================================

CREATE DATABASE IF NOT EXISTS mecaniqa;
USE mecaniqa;

-- Para recriar tudo do zero (APAGA OS DADOS), descomente as linhas abaixo:
-- DROP TABLE IF EXISTS tb_item_pedido;
-- DROP TABLE IF EXISTS tb_pedido_pecas;
-- DROP TABLE IF EXISTS tb_ordem_servico;
-- DROP TABLE IF EXISTS tb_servico;
-- DROP TABLE IF EXISTS tb_peca;

-- ---------------------------------------------------------------------
-- Peça
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_peca (
    peca_id                 INT          NOT NULL AUTO_INCREMENT,
    cod_barras              VARCHAR(13),
    fornecedor              VARCHAR(30),
    quant_estoque           INT,
    preco_custo             DECIMAL(10,2),
    preco_venda             DECIMAL(10,2),
    data_cadastro           DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tamanho                 FLOAT,
    cor                     VARCHAR(15),
    categoria_peca          ENUM('MOTOR','SUSPENSAO','FREIOS','ELETRICA','ACESSORIOS'),
    data_ultima_atualizacao DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (peca_id)
    );

-- ---------------------------------------------------------------------
-- Serviço
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_servico (
    servico_id              INT NOT NULL AUTO_INCREMENT,
    nome_servico            VARCHAR(30),
    tempo_estimado          INT,
    custo_tabelado          DECIMAL(10,2),
    data_criacao            DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_ultima_atualizacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (servico_id)
    );

-- ---------------------------------------------------------------------
-- Ordem de serviço
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_ordem_servico (
    id_ordem_servico     INT NOT NULL AUTO_INCREMENT,
    status_ordem_servico ENUM('ABERTO','PENDENTE_PAGAMENTO','PAGO','EM_EXECUCAO','EXECUTADO'),
    PRIMARY KEY (id_ordem_servico)
    );

-- ---------------------------------------------------------------------
-- Pedido de peças
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_pedido_pecas (
    id_pedido_pecas     INT NOT NULL AUTO_INCREMENT,
    peca_id             INT,
    status_pedido_pecas ENUM('ORCANDO','PENDENTE_PAGAMENTO','PAGO','ENTREGUE'),
    PRIMARY KEY (id_pedido_pecas),
    CONSTRAINT tb_pedido_pecas_ibfk_1 FOREIGN KEY (peca_id) REFERENCES tb_peca (peca_id)
    );

-- ---------------------------------------------------------------------
-- Itens do pedido (relação 1:N com tb_pedido_pecas)
-- ---------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_item_pedido (
    id_item_pedido  INT NOT NULL AUTO_INCREMENT,
    peca_id         INT,
    quantidade      INT,
    id_pedido_pecas INT NOT NULL,
    PRIMARY KEY (id_item_pedido),
    CONSTRAINT tb_item_pedido_ibfk_1  FOREIGN KEY (peca_id) REFERENCES tb_peca (peca_id),
    CONSTRAINT fk_item_pedido_pedido  FOREIGN KEY (id_pedido_pecas) REFERENCES tb_pedido_pecas (id_pedido_pecas)
    ON DELETE CASCADE
    );