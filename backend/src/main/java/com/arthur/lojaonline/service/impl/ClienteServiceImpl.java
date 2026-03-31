package com.arthur.lojaonline.service.impl;

import com.arthur.lojaonline.dto.request.ClienteRequest;
import com.arthur.lojaonline.dto.response.ClienteResponse;
import com.arthur.lojaonline.model.entity.Cliente;
import com.arthur.lojaonline.repository.ClienteRepository;
import com.arthur.lojaonline.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteResponse cadastrarCliente(ClienteRequest request) {
        validarCpfExistente(request.getCpf());
        validarEmailExistente(request.getEmail());
        validarTelefoneExistente(request.getTelefone());

        Cliente cliente = new Cliente();
        cliente.setNomeCompleto(request.getNomeCompleto());
        cliente.setCpf(request.getCpf());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        cliente.setNascimento(request.getNascimento());

        clienteRepository.save(cliente);

        return converterParaResponse(cliente);
    }

    @Override
    public ClienteResponse buscarPorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return converterParaResponse(cliente);
    }

    @Override
    public List<ClienteResponse> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(this::converterParaResponse).toList();
    }

    @Override
    public ClienteResponse atualizarCliente(Integer id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        if (!cliente.getCpf().equals(request.getCpf())) {
            validarCpfExistente(request.getCpf());
        }
        if (!cliente.getEmail().equals(request.getEmail())) {
            validarEmailExistente(request.getEmail());
        }
        if (!cliente.getTelefone().equals(request.getTelefone())) {
            validarTelefoneExistente(request.getTelefone());
        }

        cliente.setNomeCompleto(request.getNomeCompleto());
        cliente.setCpf(request.getCpf());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        cliente.setNascimento(request.getNascimento());

        clienteRepository.save(cliente);

        return converterParaResponse(cliente);
    }

    @Override
    public void deletarCliente(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        clienteRepository.deleteById(id);
    }

    private ClienteResponse converterParaResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNomeCompleto(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone());
    }

    private void validarCpfExistente(String cpf) {
        if (clienteRepository.existsByCpf(cpf)) {
            throw new RuntimeException("CPF já cadastrado no sistema.");
        }
    }

    private void validarEmailExistente(String email) {
        if (clienteRepository.existsByEmail(email)) {
            throw new RuntimeException("E-mail já cadastrado no sistema.");
        }
    }

    private void validarTelefoneExistente(String telefone) {
        if (clienteRepository.existsByTelefone(telefone)) {
            throw new RuntimeException("Telefone já cadastrado no sistema.");
        }
    }
}