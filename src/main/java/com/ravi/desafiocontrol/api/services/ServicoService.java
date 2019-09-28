package com.ravi.desafiocontrol.api.services;

import java.util.List;
import java.util.Optional;

import com.ravi.desafiocontrol.api.entities.Servico;
import com.ravi.desafiocontrol.api.dtos.ServicoDto;

public interface ServicoService {
	
	/**
	 * Retorna um Serviço dado um Id.
	 * 
	 * @param id
	 * @return Optional<Servico>
	 * 
	 */
	Optional <Servico> buscarPorId(Long id);
	
	/**
	 * Persiste um novo serviço na base de dados.
	 * 
	 * @param servico
	 * @return Servico
	 */
	Servico persistir(Servico servico);
	
	/**
	 * Remove um dado serviço da base de dados.
	 * 	
	 * @param servico
	 */
	void remover(Servico servico);
	
	/**
	 * Lista todos os serviços cadastrados no banco de dados.
	 * @return List<ServicoDto>
	 */
	List<ServicoDto> listar();
}
