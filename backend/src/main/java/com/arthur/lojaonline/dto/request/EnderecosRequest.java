package com.arthur.lojaonline.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecosRequest {

    @NotNull(message = "O ID do cliente é obrigatório.")
    private Long clienteId;

    @NotNull(message = "O tipo de endereço é obrigatório.")
    private Long tipoEnderecoId;

    @NotBlank(message = "O CEP é obrigatório.")
    @Size(min = 8, max = 8, message = "O CEP deve conter exatamente 8 caracteres.")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter exatamente 8 números.")
    private String cep;

    @NotBlank(message = "A rua é obrigatória.")
    @Size(max = 100, message = "A rua deve ter no máximo 100 caracteres.")
    private String rua;

    @NotNull(message = "O número é obrigatório.")
    @Positive(message = "O número deve ser maior que zero.")
    private Integer numero;

    @Size(max = 100, message = "O complemento deve ter no máximo 100 caracteres.")
    private String complemento;

    @NotBlank(message = "O bairro é obrigatório.")
    @Size(max = 100, message = "O bairro deve ter no máximo 100 caracteres.")
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória.")
    @Size(max = 100, message = "A cidade deve ter no máximo 100 caracteres.")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório.")
    @Size(min = 2, max = 2, message = "O estado deve conter exatamente 2 caracteres.")
    private String estado;

    @NotNull(message = "É obrigatório informar se o endereço é principal.")
    private Boolean principal;
}