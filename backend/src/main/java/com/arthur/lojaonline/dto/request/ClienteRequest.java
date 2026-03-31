package com.arthur.lojaonline.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ClienteRequest {

    private String nomeCompleto;
    private String cpf;
    private LocalDate nascimento;
    private String email;
    private String telefone;
}