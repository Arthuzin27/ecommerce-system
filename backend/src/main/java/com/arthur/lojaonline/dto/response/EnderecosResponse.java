package com.arthur.lojaonline.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EnderecosResponse {

    private Long id;
    private String cep;
    private String rua;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private boolean principal;
    private Integer tipoEnderecoId;
    private String tipoEnderecoNome;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    
    public EnderecosResponse(Long id, String cep, String rua, Integer numero, String complemento, String bairro,
            String cidade, String estado, boolean principal, Integer tipoEnderecoId, String tipoEnderecoNome) {
        this.id = id;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.principal = principal;
        this.tipoEnderecoId = tipoEnderecoId;
        this.tipoEnderecoNome = tipoEnderecoNome;
    }

}
