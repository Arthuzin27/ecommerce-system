package com.arthur.lojaonline.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoResponse {
    
    private Long id;
    private String nome;
    private String descricao;
}