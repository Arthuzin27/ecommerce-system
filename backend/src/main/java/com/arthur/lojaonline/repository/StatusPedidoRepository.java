package com.arthur.lojaonline.repository;

import com.arthur.lojaonline.model.entity.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusPedidoRepository extends JpaRepository<StatusPedido, Long> {

    boolean existsByNome(String nome);
}