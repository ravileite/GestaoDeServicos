package com.ravi.desafiocontrol.api.services;

import java.util.List;
import java.util.Optional;

import com.ravi.desafiocontrol.api.entities.Ordem;
import com.ravi.desafiocontrol.api.dtos.OrdemDto;

public interface OrdemService {
	
	/**
	 * Retorna uma ordem dado um Id;
	 * 
	 * @param id
	 * @return Optional<Ordem>
	 */
	Optional <Ordem> buscarPorId(Long id);
	
	/**
	 * Persiste uma ordem de serviço na base de dados;
	 * 
	 * @param ordem
	 * @return Ordem
	 */
	Ordem persistir(Ordem ordem);
	
	/**
	 * Remove uma dada ordem da base de dados.
	 * 
	 * @param ordem
	 */
	void remover(Ordem ordem);
	
	/**
	 * Lista todas as ordens de serviço cadastradas na base de dados.
	 * @return
	 */
	List<OrdemDto> listar();
}
