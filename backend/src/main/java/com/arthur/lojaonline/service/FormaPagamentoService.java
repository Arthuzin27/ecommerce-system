package com.arthur.lojaonline.service;

import com.arthur.lojaonline.dto.request.FormaPagamentoRequest;
import com.arthur.lojaonline.dto.response.FormaPagamentoResponse;

import java.util.List;

public interface FormaPagamentoService {
    
    FormaPagamentoResponse criar(FormaPagamentoRequest request);
    
    FormaPagamentoResponse buscarPorId(Long id);
    
    List<FormaPagamentoResponse> listarTodos();
    
    FormaPagamentoResponse atualizar(Long id, FormaPagamentoRequest request);
    
    void deletar(Long id);
}