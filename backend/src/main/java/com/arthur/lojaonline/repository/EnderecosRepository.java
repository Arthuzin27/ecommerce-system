package com.arthur.lojaonline.repository;

import com.arthur.lojaonline.model.entity.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnderecosRepository extends JpaRepository<Enderecos, Long>{
    
    List<Enderecos> findByClienteId(Long clienteId);

    Optional<Enderecos> findByClienteIdAndPrincipalTrue(Long clienteId);

    Optional<Enderecos> findByIdAndClienteId(Long id, Long clienteId);
}
