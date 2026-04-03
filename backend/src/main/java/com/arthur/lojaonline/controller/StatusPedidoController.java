package com.arthur.lojaonline.controller;

import com.arthur.lojaonline.dto.request.StatusPedidoRequest;
import com.arthur.lojaonline.dto.response.StatusPedidoResponse;
import com.arthur.lojaonline.service.StatusPedidoService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/status-pedido")
@RequiredArgsConstructor
public class StatusPedidoController {
    
    private final StatusPedidoService statusPedidoService;
    
    @PostMapping
    public ResponseEntity<StatusPedidoResponse> criar(@Valid @RequestBody StatusPedidoRequest request) {
        StatusPedidoResponse response = statusPedidoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<StatusPedidoResponse> buscarPorId(@PathVariable Long id) {
        StatusPedidoResponse response = statusPedidoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<StatusPedidoResponse>> listarTodos() {
        List<StatusPedidoResponse> responses = statusPedidoService.listarTodos();
        return ResponseEntity.ok(responses);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<StatusPedidoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody StatusPedidoRequest request) {
        StatusPedidoResponse response = statusPedidoService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        statusPedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}