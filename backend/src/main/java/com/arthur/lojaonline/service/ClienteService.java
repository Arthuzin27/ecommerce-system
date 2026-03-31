package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.ClienteRequest;
import com.arthur.lojaonline.dto.response.ClienteResponse;

import java.util.List;

public interface ClienteService {

    ClienteResponse cadastrarCliente(ClienteRequest request);

    ClienteResponse buscarPorId(Integer id);

    List<ClienteResponse> listarTodos();

    ClienteResponse atualizarCliente(Integer id, ClienteRequest request);

    void deletarCliente(Integer id);
}