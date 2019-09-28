package com.ravi.desafiocontrol.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.desafiocontrol.api.entities.Ordem;
import com.ravi.desafiocontrol.api.repositories.OrdemRepository;
import com.ravi.desafiocontrol.api.services.OrdemService;
import com.ravi.desafiocontrol.api.util.OrdemUtil;
import com.ravi.desafiocontrol.api.dtos.OrdemDto;

@Service
public class OrdemServiceImpl implements OrdemService{
	
	private static final Logger log = LoggerFactory.getLogger(ServicoServiceImpl.class);
	
	OrdemUtil util = new OrdemUtil();

	@Autowired
	private OrdemRepository ordemRepository;
	
	@Override
	public Optional<Ordem> buscarPorId(Long id) {
		log.info("Buscando uma ordem de serviço de id {}", id);
		return Optional.ofNullable(ordemRepository.findOrdemById(id));
	}
	
	@Override
	public Ordem persistir(Ordem ordem) {
		log.info("Persistindo a nova ordem de serviço: {}", ordem);
		return this.ordemRepository.save(ordem);
	}
	
	@Override
	public void remover(Ordem ordem) {
		log.info("Removendo da base de dados a ordem de serviço: {}", ordem);
		this.ordemRepository.delete(ordem);
	}
	
	@Override
	public List<OrdemDto> listar() {
		log.info("Listando ordens de serviço.");
		List<Ordem> listaOrdem = this.ordemRepository.findAll();
		List<OrdemDto> listaOrdemDto = new ArrayList<OrdemDto>();
		
		for (Ordem ordem : listaOrdem) {
			listaOrdemDto.add(util.converteOrdemParaDto(ordem));
		}
		
		return listaOrdemDto;
	}
}
