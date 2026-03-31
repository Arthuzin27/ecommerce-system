CREATE DATABASE IF NOT EXISTS loja_online;
USE loja_online;

----------------------------------------------

CREATE TABLE clientes(
    id int NOT NULL AUTO_INCREMENT,
    nome_completo varchar(100) NOT NULL,
    cpf char(11) NOT NULL,
    nascimento date NOT NULL,
    email varchar(100) NOT NULL,
    telefone varchar(20) NOT NULL,
    data_cadastro datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT pk_clientes PRIMARY KEY(id),
    CONSTRAINT uq_clientes_cpf UNIQUE(cpf),
    CONSTRAINT uq_clientes_email UNIQUE(email)
) DEFAULT CHARSET = utf8mb4;

----------------------------------------------

CREATE TABLE tipos_enderecos(
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(20) NOT NULL,
    
    CONSTRAINT pk_tipos_enderecos PRIMARY KEY(id),
    CONSTRAINT uq_tipos_enderecos_nome UNIQUE(nome)
) DEFAULT CHARSET = utf8mb4;

----------------------------------------------

CREATE TABLE enderecos(
    id int NOT NULL AUTO_INCREMENT,
    cliente_id int NOT NULL,
    tipo_endereco_id int NOT NULL,
    cep char(8) NOT NULL,
    rua varchar(100) NOT NULL,
    numero int NOT NULL,
    complemento varchar(100),
    bairro varchar(100) NOT NULL,
    cidade varchar(100) NOT NULL,
    estado varchar(100) NOT NULL,
    principal boolean NOT NULL DEFAULT false,
    data_cadastro datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT pk_enderecos PRIMARY KEY(id),
    
    CONSTRAINT fk_enderecos_clientes 
    FOREIGN KEY(cliente_id) 
    REFERENCES clientes(id) ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT fk_enderecos_tipos_enderecos 
    FOREIGN KEY(tipo_endereco_id) 
    REFERENCES tipos_enderecos(id) ON DELETE RESTRICT ON UPDATE CASCADE
) DEFAULT CHARSET = utf8mb4;

----------------------------------------------

CREATE TABLE categorias(
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(100) NOT NULL,
    descricao text,
    
    CONSTRAINT pk_categorias PRIMARY KEY(id),
    CONSTRAINT uq_categorias_nome UNIQUE(nome)
) DEFAULT CHARSET = utf8mb4;

----------------------------------------------

CREATE TABLE produtos(
    id int NOT NULL AUTO_INCREMENT,
    categoria_id int NOT NULL,
    nome varchar(100) NOT NULL,
    descricao text,
    preco decimal(8,2) NOT NULL CHECK (preco >= 0),
    estoque int NOT NULL DEFAULT 0 CHECK (estoque >= 0),
    ativo boolean NOT NULL DEFAULT true,
    data_cadastro datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT pk_produtos PRIMARY KEY(id),
    
    CONSTRAINT fk_produtos_categorias 
    FOREIGN KEY(categoria_id) 
    REFERENCES categorias(id) ON DELETE RESTRICT ON UPDATE CASCADE
) DEFAULT CHARSET = utf8mb4;

----------------------------------------------

CREATE TABLE status_pedido(
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(20) NOT NULL,
    
    CONSTRAINT pk_status_pedido PRIMARY KEY(id),
    CONSTRAINT uq_status_pedido_nome UNIQUE(nome)
) DEFAULT CHARSET = utf8mb4;

----------------------------------------------

CREATE TABLE pedidos(
    id int NOT NULL AUTO_INCREMENT,
    cliente_id int NOT NULL,
    status_id int NOT NULL,
    data_pedido datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    valor_total decimal(8,2) NOT NULL DEFAULT 0 CHECK (valor_total >= 0),
    data_atualizacao datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT pk_pedidos PRIMARY KEY(id),
    
    CONSTRAINT fk_pedidos_clientes 
    FOREIGN KEY(cliente_id) 
    REFERENCES clientes(id) ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT fk_pedidos_status_pedido 
    FOREIGN KEY(status_id) 
    REFERENCES status_pedido(id) ON DELETE RESTRICT ON UPDATE CASCADE
) DEFAULT CHARSET = utf8mb4;

----------------------------------------------

CREATE TABLE itens_pedido(
    id int NOT NULL AUTO_INCREMENT,
    pedido_id int NOT NULL,
    produto_id int NOT NULL,
    quantidade int NOT NULL CHECK (quantidade > 0),
    preco_unitario decimal(8,2) NOT NULL CHECK (preco_unitario >= 0),
    subtotal decimal(8,2) NOT NULL CHECK (subtotal >= 0),
    
    CONSTRAINT pk_itens_pedido PRIMARY KEY(id),
    
    CONSTRAINT uq_itens_pedido UNIQUE(pedido_id, produto_id),
    
    CONSTRAINT fk_itens_pedido_pedidos 
    FOREIGN KEY(pedido_id) 
    REFERENCES pedidos(id) ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT fk_itens_pedido_produtos 
    FOREIGN KEY(produto_id) 
    REFERENCES produtos(id) ON DELETE RESTRICT ON UPDATE CASCADE
) DEFAULT CHARSET = utf8mb4;

----------------------------------------------

CREATE TABLE formas_pagamento(
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(20) NOT NULL,
    descricao text,
    
    CONSTRAINT pk_formas_pagamento PRIMARY KEY(id),
    CONSTRAINT uq_formas_pagamento_nome UNIQUE(nome)
) DEFAULT CHARSET = utf8mb4;

----------------------------------------------

CREATE TABLE status_pagamento(
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(20) NOT NULL,
    descricao text,
    
    CONSTRAINT pk_status_pagamento PRIMARY KEY(id),
    CONSTRAINT uq_status_pagamento_nome UNIQUE(nome)
) DEFAULT CHARSET = utf8mb4;

----------------------------------------------

CREATE TABLE pagamentos(
    id int NOT NULL AUTO_INCREMENT,
    pedido_id int NOT NULL,
    forma_pagamento_id int NOT NULL,
    status_pagamento_id int NOT NULL,
    valor_pago decimal(8,2) NOT NULL CHECK (valor_pago >= 0),
    data_pagamento datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT pk_pagamentos PRIMARY KEY(id),
    
    CONSTRAINT fk_pagamentos_pedidos 
    FOREIGN KEY(pedido_id) 
    REFERENCES pedidos(id) ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT fk_pagamentos_formas_pagamento 
    FOREIGN KEY(forma_pagamento_id) 
    REFERENCES formas_pagamento(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    
    CONSTRAINT fk_pagamentos_status_pagamento 
    FOREIGN KEY(status_pagamento_id) 
    REFERENCES status_pagamento(id) ON DELETE RESTRICT ON UPDATE CASCADE
) DEFAULT CHARSET = utf8mb4;

----------------------------------------------

CREATE TABLE status_entrega(
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(20) NOT NULL,
    descricao text,
    
    CONSTRAINT pk_status_entrega PRIMARY KEY(id),
    CONSTRAINT uq_status_entrega_nome UNIQUE(nome)
) DEFAULT CHARSET = utf8mb4;

----------------------------------------------

CREATE TABLE entregadores(
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(100) NOT NULL,
    telefone varchar(20) NOT NULL,
    placa_veiculo varchar(10),
    ativo boolean NOT NULL DEFAULT true,
    data_cadastro datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT pk_entregadores PRIMARY KEY(id)
) DEFAULT CHARSET = utf8mb4;

----------------------------------------------

CREATE TABLE entregas(
    id int NOT NULL AUTO_INCREMENT,
    pedido_id int NOT NULL,
    endereco_id int NOT NULL,
    entregador_id int,
    status_entrega_id int NOT NULL,
    data_envio datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_entrega datetime,
    observacoes text,
    
    CONSTRAINT pk_entregas PRIMARY KEY(id),
    CONSTRAINT uq_entregas_pedido UNIQUE(pedido_id),
    
    CONSTRAINT fk_entregas_pedidos 
    FOREIGN KEY(pedido_id) 
    REFERENCES pedidos(id) ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT fk_entregas_enderecos 
    FOREIGN KEY(endereco_id) 
    REFERENCES enderecos(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    
    CONSTRAINT fk_entregas_entregadores
    FOREIGN KEY(entregador_id)
    REFERENCES entregadores(id) ON DELETE SET NULL ON UPDATE CASCADE,
    
    CONSTRAINT fk_entregas_status_entrega 
    FOREIGN KEY(status_entrega_id) 
    REFERENCES status_entrega(id) ON DELETE RESTRICT ON UPDATE CASCADE
) DEFAULT CHARSET = utf8mb4;

----------------------------------------------

CREATE TABLE adicionais(
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(100) NOT NULL,
    descricao text,
    preco decimal(8,2) NOT NULL CHECK (preco >= 0),
    ativo boolean NOT NULL DEFAULT true,
    data_cadastro datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT pk_adicionais PRIMARY KEY(id),
    CONSTRAINT uq_adicionais_nome UNIQUE(nome)
) DEFAULT CHARSET = utf8mb4;

----------------------------------------------

CREATE TABLE item_pedido_adicionais(
    id int NOT NULL AUTO_INCREMENT,
    item_pedido_id int NOT NULL,
    adicional_id int NOT NULL,
    quantidade int NOT NULL DEFAULT 1 CHECK (quantidade > 0),
    subtotal_adicional decimal(8,2) NOT NULL CHECK (subtotal_adicional >= 0),
    
    CONSTRAINT pk_item_pedido_adicionais PRIMARY KEY(id),
    CONSTRAINT uq_item_pedido_adicionais UNIQUE(item_pedido_id, adicional_id),
    
    CONSTRAINT fk_item_pedido_adicionais_itens_pedido
    FOREIGN KEY(item_pedido_id)
    REFERENCES itens_pedido(id) ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT fk_item_pedido_adicionais_adicionais
    FOREIGN KEY(adicional_id)
    REFERENCES adicionais(id) ON DELETE RESTRICT ON UPDATE CASCADE
) DEFAULT CHARSET = utf8mb4;