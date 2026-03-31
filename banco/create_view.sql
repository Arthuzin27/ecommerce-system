USE loja_online;

-- VIEW DE CLIENTES COM ENDERECOS
----------------------------------------------

CREATE VIEW vw_clientes_enderecos AS
SELECT
    c.id AS cliente_id,
    c.nome_completo,
    c.cpf,
    c.email,
    c.telefone,
    e.id AS endereco_id,
    te.nome AS tipo_endereco,
    e.cep,
    e.rua,
    e.numero,
    e.complemento,
    e.bairro,
    e.cidade,
    e.estado,
    e.principal
FROM clientes c
INNER JOIN enderecos e ON c.id = e.cliente_id
INNER JOIN tipos_enderecos te ON e.tipo_endereco_id = te.id;


-- VIEW DE PRODUTOS COM CATEGORIAS
----------------------------------------------

CREATE VIEW vw_produtos_categorias AS
SELECT
    p.id AS produto_id,
    p.nome AS produto,
    c.nome AS categoria,
    p.descricao,
    p.preco,
    p.estoque,
    p.ativo,
    p.data_cadastro
FROM produtos p
INNER JOIN categorias c ON p.categoria_id = c.id;


-- VIEW DE PEDIDOS COMPLETOS
----------------------------------------------

CREATE VIEW vw_pedidos_completos AS
SELECT
    p.id AS pedido_id,
    c.nome_completo AS cliente,
    sp.nome AS status_pedido,
    p.data_pedido,
    p.valor_total
FROM pedidos p
INNER JOIN clientes c ON p.cliente_id = c.id
INNER JOIN status_pedido sp ON p.status_id = sp.id;


-- VIEW DE ITENS DOS PEDIDOS
----------------------------------------------

CREATE VIEW vw_itens_pedido AS
SELECT
    ip.id AS item_pedido_id,
    ip.pedido_id,
    pr.nome AS produto,
    ip.quantidade,
    ip.preco_unitario,
    ip.subtotal
FROM itens_pedido ip
INNER JOIN produtos pr ON ip.produto_id = pr.id;


-- VIEW DE PAGAMENTOS DETALHADOS
----------------------------------------------

CREATE VIEW vw_pagamentos_detalhados AS
SELECT
    pg.id AS pagamento_id,
    pg.pedido_id,
    fp.nome AS forma_pagamento,
    sp.nome AS status_pagamento,
    pg.valor_pago,
    pg.data_pagamento
FROM pagamentos pg
INNER JOIN formas_pagamento fp ON pg.forma_pagamento_id = fp.id
INNER JOIN status_pagamento sp ON pg.status_pagamento_id = sp.id;


-- VIEW DE ENTREGAS DETALHADAS
----------------------------------------------

CREATE VIEW vw_entregas_detalhadas AS
SELECT
    e.id AS entrega_id,
    e.pedido_id,
    c.nome_completo AS cliente,
    en.rua,
    en.numero,
    en.bairro,
    en.cidade,
    en.estado,
    se.nome AS status_entrega,
    ent.nome AS entregador,
    e.data_envio,
    e.data_entrega,
    e.observacoes
FROM entregas e
INNER JOIN pedidos p ON e.pedido_id = p.id
INNER JOIN clientes c ON p.cliente_id = c.id
INNER JOIN enderecos en ON e.endereco_id = en.id
INNER JOIN status_entrega se ON e.status_entrega_id = se.id
LEFT JOIN entregadores ent ON e.entregador_id = ent.id;


-- VIEW DE ADICIONAIS DOS ITENS
----------------------------------------------

CREATE VIEW vw_itens_pedido_adicionais AS
SELECT
    ipa.id AS item_pedido_adicional_id,
    ipa.item_pedido_id,
    pr.nome AS produto,
    a.nome AS adicional,
    ipa.quantidade,
    ipa.subtotal_adicional
FROM item_pedido_adicionais ipa
INNER JOIN itens_pedido ip ON ipa.item_pedido_id = ip.id
INNER JOIN produtos pr ON ip.produto_id = pr.id
INNER JOIN adicionais a ON ipa.adicional_id = a.id;


-- VIEW DE RESUMO GERAL DOS PEDIDOS
----------------------------------------------

CREATE VIEW vw_resumo_pedidos AS
SELECT
    p.id AS pedido_id,
    c.nome_completo AS cliente,
    sp.nome AS status_pedido,
    COUNT(DISTINCT ip.id) AS total_itens,
    IFNULL(SUM(ip.quantidade), 0) AS quantidade_total_produtos,
    p.valor_total,
    p.data_pedido
FROM pedidos p
INNER JOIN clientes c ON p.cliente_id = c.id
INNER JOIN status_pedido sp ON p.status_id = sp.id
LEFT JOIN itens_pedido ip ON p.id = ip.pedido_id
GROUP BY
    p.id,
    c.nome_completo,
    sp.nome,
    p.valor_total,
    p.data_pedido;