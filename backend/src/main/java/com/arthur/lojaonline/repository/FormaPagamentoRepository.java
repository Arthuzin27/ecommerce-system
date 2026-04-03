package com.arthur.lojaonline.repository;

import com.arthur.lojaonline.model.entity.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
    boolean existsByNome(String nome);
}