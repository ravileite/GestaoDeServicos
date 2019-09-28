package com.ravi.desafiocontrol.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ravi.desafiocontrol.api.entities.Servico;


public interface ServicoRepository extends JpaRepository<Servico, Long>{

	@Transactional(readOnly = true)
	Servico findServicoById(Long Id);
	
}	
