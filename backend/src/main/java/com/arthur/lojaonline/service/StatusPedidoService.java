package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.StatusPedidoRequest;
import com.arthur.lojaonline.dto.response.StatusPedidoResponse;

import java.util.List;

public interface StatusPedidoService {
    
    StatusPedidoResponse criar(StatusPedidoRequest request);
    
    StatusPedidoResponse buscarPorId(Long id);
    
    List<StatusPedidoResponse> listarTodos();
    
    StatusPedidoResponse atualizar(Long id, StatusPedidoRequest request);
    
    void deletar(Long id);
}