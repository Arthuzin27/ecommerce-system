package com.arthur.lojaonline.service.impl;

import com.arthur.lojaonline.dto.request.StatusPedidoRequest;
import com.arthur.lojaonline.dto.response.StatusPedidoResponse;
import com.arthur.lojaonline.model.entity.StatusPedido;
import com.arthur.lojaonline.repository.StatusPedidoRepository;
import com.arthur.lojaonline.service.StatusPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusPedidoServiceImpl implements StatusPedidoService {
    
    private final StatusPedidoRepository statusPedidoRepository;
    
    @Override
    @Transactional
    public StatusPedidoResponse criar(StatusPedidoRequest request) {
        if (statusPedidoRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Status de pedido já existe com este nome: " + request.getNome());
        }
        
        StatusPedido status = new StatusPedido();
        status.setNome(request.getNome());
        
        StatusPedido saved = statusPedidoRepository.save(status);
        return toResponse(saved);
    }
    
    @Override
    public StatusPedidoResponse buscarPorId(Long id) {
        StatusPedido status = statusPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status de pedido não encontrado com ID: " + id));
        return toResponse(status);
    }
    
    @Override
    public List<StatusPedidoResponse> listarTodos() {
        return statusPedidoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public StatusPedidoResponse atualizar(Long id, StatusPedidoRequest request) {
        StatusPedido status = statusPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status de pedido não encontrado com ID: " + id));
        
        if (!status.getNome().equals(request.getNome()) && 
            statusPedidoRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Status de pedido já existe com este nome: " + request.getNome());
        }
        
        status.setNome(request.getNome());
        StatusPedido updated = statusPedidoRepository.save(status);
        return toResponse(updated);
    }
    
    @Override
    @Transactional
    public void deletar(Long id) {
        if (!statusPedidoRepository.existsById(id)) {
            throw new RuntimeException("Status de pedido não encontrado com ID: " + id);
        }
        statusPedidoRepository.deleteById(id);
    }
    
    private StatusPedidoResponse toResponse(StatusPedido status) {
        return new StatusPedidoResponse(status.getId(), status.getNome());
    }
}