package com.ravi.desafiocontrol.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ravi.desafiocontrol.api.entities.Ordem;

public interface OrdemRepository extends JpaRepository<Ordem, Long>{
	
	@Transactional(readOnly = true)
	Ordem findOrdemById(Long id);
	
}
