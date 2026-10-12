create database if not exists MECANIQA_DB;

use MECANIQA_DB;

create table if not exists tb_peca(
    peca_id int PRIMARY KEY,
    cod_barras varchar(13),
    fornecedor varchar(30),
    quant_estoque int,
    preco_custo decimal,
    preco_venda decimal,
    data_cadastro datetime not null default current_timestamp,
    tamanho decimal,
    cor varchar(15),
    categoria_peca ENUM('CategoriaPeca', 'StatusOrdemServico', 'StatusPedidoPecas'),
    data_ultima_atualizacao datetime not null default current_timestamp
);

create table if not exists tb_servico(
    servico_id int PRIMARY KEY,
    nome_servico VARCHAR(30),
    tempo_estimado int,
    custo_tabelado decimal,
    data_criacao datetime not null default current_timestamp,
    data_ultima_atualizacao datetime not null default current_timestamp on update current_timestamp,
);

create table if not exists tb_pedido_pecas (
    id_pedido_pecas int PRIMARY KEY,
    peca_id int FOREIGN KEY,
    status_pedido_pecas ENUM('ORCANDO','PENDENTE_PAGAMENTO','PAGO','ENTREGUE')
);

create table if not exists tb_idem_pedido (
    id_item_pedido int PRIMARY KEY,
    id_peca int FOREIGN KEY,
    quantidade int
);

create table if not exists tb_ordem_servico (
    id_ordem_servico int PRIMARY KEY,
    status_ordem_servico ENUM('ORCANDO','PENDENTE_PAGAMENTO','PAGO','ENTREGUE')
);

