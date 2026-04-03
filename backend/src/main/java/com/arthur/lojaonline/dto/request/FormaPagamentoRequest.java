package com.arthur.lojaonline.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoRequest {
    
    @NotBlank(message = "O nome da forma de pagamento é obrigatório")
    @Size(max = 20, message = "O nome deve ter no máximo 20 caracteres")
    private String nome;
    
    private String descricao;
}