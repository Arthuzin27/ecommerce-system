package com.arthur.lojaonline.service.impl;

import com.arthur.lojaonline.dto.response.TipoEnderecoResponse;
import com.arthur.lojaonline.model.entity.TipoEndereco;
import com.arthur.lojaonline.repository.TipoEnderecoRepository;
import com.arthur.lojaonline.service.TipoEnderecoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEnderecoServiceImpl implements TipoEnderecoService {

    private final TipoEnderecoRepository tipoEnderecoRepository;

    public TipoEnderecoServiceImpl(TipoEnderecoRepository tipoEnderecoRepository) {
        this.tipoEnderecoRepository = tipoEnderecoRepository;
    }

    @Override
    public List<TipoEnderecoResponse> listarTodos() {
        return tipoEnderecoRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    @Override
    public TipoEnderecoResponse buscarPorId(Long id) {
        TipoEndereco tipoEndereco = tipoEnderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de endereço não encontrado."));

        return converterParaResponse(tipoEndereco);
    }

    private TipoEnderecoResponse converterParaResponse(TipoEndereco tipoEndereco) {
        TipoEnderecoResponse response = new TipoEnderecoResponse();
        response.setId(tipoEndereco.getId());
        response.setNome(tipoEndereco.getNome());
        return response;
    }
}