package com.arthur.lojaonline.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ClienteResponse {

    private Long id;
    private String nomeCompleto;
    private String cpf;
    private LocalDate nascimento;
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

    public ClienteResponse(Long id, String nomeCompleto, String cpf, String email, String telefone) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }
}