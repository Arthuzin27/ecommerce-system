package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.response.TipoEnderecoResponse;

import java.util.List;

public interface TipoEnderecoService {

    List<TipoEnderecoResponse> listarTodos();

    TipoEnderecoResponse buscarPorId(Long id);
}