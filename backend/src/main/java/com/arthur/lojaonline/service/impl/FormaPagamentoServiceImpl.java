package com.arthur.lojaonline.service.impl;

import com.arthur.lojaonline.dto.request.FormaPagamentoRequest;
import com.arthur.lojaonline.dto.response.FormaPagamentoResponse;
import com.arthur.lojaonline.model.entity.FormaPagamento;
import com.arthur.lojaonline.repository.FormaPagamentoRepository;
import com.arthur.lojaonline.service.FormaPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormaPagamentoServiceImpl implements FormaPagamentoService {
    
    private final FormaPagamentoRepository formaPagamentoRepository;
    
    @Override
    @Transactional
    public FormaPagamentoResponse criar(FormaPagamentoRequest request) {
        if (formaPagamentoRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Forma de pagamento já existe com este nome: " + request.getNome());
        }
        
        FormaPagamento forma = new FormaPagamento();
        forma.setNome(request.getNome());
        forma.setDescricao(request.getDescricao());
        
        FormaPagamento saved = formaPagamentoRepository.save(forma);
        return toResponse(saved);
    }
    
    @Override
    public FormaPagamentoResponse buscarPorId(Long id) {
        FormaPagamento forma = formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forma de pagamento não encontrada com ID: " + id));
        return toResponse(forma);
    }
    
    @Override
    public List<FormaPagamentoResponse> listarTodos() {
        return formaPagamentoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public FormaPagamentoResponse atualizar(Long id, FormaPagamentoRequest request) {
        FormaPagamento forma = formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forma de pagamento não encontrada com ID: " + id));
        
        if (!forma.getNome().equals(request.getNome()) && 
            formaPagamentoRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Forma de pagamento já existe com este nome: " + request.getNome());
        }
        
        forma.setNome(request.getNome());
        forma.setDescricao(request.getDescricao());
        
        FormaPagamento updated = formaPagamentoRepository.save(forma);
        return toResponse(updated);
    }
    
    @Override
    @Transactional
    public void deletar(Long id) {
        if (!formaPagamentoRepository.existsById(id)) {
            throw new RuntimeException("Forma de pagamento não encontrada com ID: " + id);
        }
        formaPagamentoRepository.deleteById(id);
    }
    
    private FormaPagamentoResponse toResponse(FormaPagamento forma) {
        return new FormaPagamentoResponse(forma.getId(), forma.getNome(), forma.getDescricao());
    }
}