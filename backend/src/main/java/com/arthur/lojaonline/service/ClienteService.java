package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.ClienteRequest;
import com.arthur.lojaonline.dto.response.ClienteResponse;

import java.util.List;

public interface ClienteService {

    ClienteResponse cadastrarCliente(ClienteRequest request);

    ClienteResponse buscarPorId(Long id);

    List<ClienteResponse> listarTodos();

    ClienteResponse atualizarCliente(Long id, ClienteRequest request);

    void deletarCliente(Long id);
}