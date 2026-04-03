package com.arthur.lojaonline.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "formas_pagamento")
@Getter
@Setter
@NoArgsConstructor
public class FormaPagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String nome;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
}