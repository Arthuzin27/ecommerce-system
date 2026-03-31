USE loja_online;

INSERT INTO tipos_enderecos (nome) VALUES
('Casa'),
('Trabalho'),
('Cobranca'),
('Outro');



INSERT INTO status_pedido (nome) VALUES
('Pendente'),
('Confirmado'),
('Em preparo'),
('Saiu para entrega'),
('Entregue'),
('Cancelado');



INSERT INTO formas_pagamento (nome, descricao) VALUES
('Pix', 'Pagamento instantaneo via Pix'),
('Cartao de Credito', 'Pagamento realizado no cartao de credito'),
('Cartao de Debito', 'Pagamento realizado no cartao de debito'),
('Dinheiro', 'Pagamento em dinheiro no ato da entrega');



INSERT INTO status_pagamento (nome, descricao) VALUES
('Pendente', 'Pagamento aguardando confirmacao'),
('Pago', 'Pagamento aprovado com sucesso'),
('Recusado', 'Pagamento recusado pela operadora ou sistema'),
('Cancelado', 'Pagamento cancelado'),
('Reembolsado', 'Pagamento devolvido ao cliente');



INSERT INTO status_entrega (nome, descricao) VALUES
('Aguardando preparo', 'Pedido ainda esta em preparacao'),
('Em rota', 'Pedido saiu para entrega'),
('Entregue', 'Pedido entregue ao cliente'),
('Falha na entrega', 'Nao foi possivel concluir a entrega'),
('Cancelada', 'Entrega cancelada');



INSERT INTO categorias (nome, descricao) VALUES
('Hamburgueres', 'Lanches e hamburgueres artesanais'),
('Pizzas', 'Pizzas salgadas e doces'),
('Bebidas', 'Refrigerantes, sucos e outras bebidas'),
('Sobremesas', 'Doces e sobremesas'),
('Combos', 'Combos promocionais');



INSERT INTO adicionais (nome, descricao, preco, ativo) VALUES
('Queijo extra', 'Adicional de queijo', 3.00, true),
('Bacon', 'Adicional de bacon crocante', 4.50, true),
('Ovo', 'Adicional de ovo', 2.50, true),
('Catupiry', 'Adicional de catupiry', 3.50, true),
('Molho especial', 'Adicional de molho especial da casa', 2.00, true),
('Calabresa', 'Adicional de calabresa', 4.00, true),
('Cheddar', 'Adicional de cheddar cremoso', 3.50, true);