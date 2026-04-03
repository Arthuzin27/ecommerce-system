package com.arthur.lojaonline.controller;

import com.arthur.lojaonline.dto.request.FormaPagamentoRequest;
import com.arthur.lojaonline.dto.response.FormaPagamentoResponse;
import com.arthur.lojaonline.service.FormaPagamentoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formas-pagamento")
@RequiredArgsConstructor
public class FormaPagamentoController {
    
    private final FormaPagamentoService formaPagamentoService;
    
    @PostMapping
    public ResponseEntity<FormaPagamentoResponse> criar(@Valid @RequestBody FormaPagamentoRequest request) {
        FormaPagamentoResponse response = formaPagamentoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamentoResponse> buscarPorId(@PathVariable Long id) {
        FormaPagamentoResponse response = formaPagamentoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<FormaPagamentoResponse>> listarTodos() {
        List<FormaPagamentoResponse> responses = formaPagamentoService.listarTodos();
        return ResponseEntity.ok(responses);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamentoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody FormaPagamentoRequest request) {
        FormaPagamentoResponse response = formaPagamentoService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        formaPagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}