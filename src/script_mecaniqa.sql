create database if not exists MECANIQA;

use MECANIQA;

create table if not exists tb_peca(
    peca_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cod_barras varchar(13),
    fornecedor varchar(30),
    quant_estoque int,
    preco_custo decimal,
    preco_venda decimal,
    data_cadastro datetime not null default current_timestamp,
    tamanho decimal,
    cor varchar(15),
    categoria_peca ENUM('MOTOR','SUSPENSAO','FREIOS','ELETRICA','ACESSORIOS'),
    data_ultima_atualizacao datetime not null default current_timestamp on update current_timestamp
    );

ALTER TABLE tb_peca MODIFY peca_id INT NOT NULL AUTO_INCREMENT;

create table if not exists tb_servico(
    servico_id int PRIMARY KEY,
    nome_servico VARCHAR(30),
    tempo_estimado int,
    custo_tabelado decimal,
    data_criacao datetime not null default current_timestamp,
    data_ultima_atualizacao datetime not null default current_timestamp on update current_timestamp
    );

create table if not exists tb_pedido_pecas (
    id_pedido_pecas int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    peca_id int,
    FOREIGN KEY (peca_id) references tb_peca(peca_id),
    status_pedido_pecas ENUM('ORCANDO','PENDENTE_PAGAMENTO','PAGO','ENTREGUE')
    );

create table if not exists tb_idem_pedido (
    id_item_pedido int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_pedido_pecas int NOT NULL,
    peca_id int,
    FOREIGN KEY (id_pedido_pecas) references tb_pedido_pecas(id_pedido_pecas) ON DELETE CASCADE,
    FOREIGN KEY (peca_id) references tb_peca(peca_id),
    quantidade int
    );

create table if not exists tb_ordem_servico (
     id_ordem_servico int PRIMARY KEY,
     status_ordem_servico ENUM('ORCANDO','PENDENTE_PAGAMENTO','PAGO','ENTREGUE')
    );
