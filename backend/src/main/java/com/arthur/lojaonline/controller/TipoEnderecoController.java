package com.arthur.lojaonline.controller;

import com.arthur.lojaonline.dto.response.TipoEnderecoResponse;
import com.arthur.lojaonline.service.TipoEnderecoService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-enderecos")
@RequiredArgsConstructor
public class TipoEnderecoController {

    private final TipoEnderecoService tipoEnderecoService;

    @GetMapping
    public ResponseEntity<List<TipoEnderecoResponse>> listarTodos() {
        return ResponseEntity.ok(tipoEnderecoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEnderecoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tipoEnderecoService.buscarPorId(id));
    }
}