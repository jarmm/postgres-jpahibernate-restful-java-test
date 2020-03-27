package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CreditoCliente;

@Repository
public interface CreditoClienteRepository  extends JpaRepository<CreditoCliente, Long>{

}
