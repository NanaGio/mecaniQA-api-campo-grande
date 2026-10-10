create database if not exists MECANIQA_DB;

use MECANIQA_DB;

create table if not exists tb_peca(
    peca_id int PRIMARY KEY,
    cod_barras varchar(13),
    fornecedor varchar(30),
    quant_estoque int,
    preco_custo decimal,
    preco_venda decimal,
    data_cadastro datetime,
    tamanho decimal,
    cor varchar(15),
    categoria_peca ENUM('CategoriaPeca', 'StatusOrdemServico', 'StatusPedidoPecas'),
    data_ultima_atualizacao datetime
);