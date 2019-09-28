package com.ravi.desafiocontrol.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.desafiocontrol.api.entities.Servico;
import com.ravi.desafiocontrol.api.repositories.ServicoRepository;
import com.ravi.desafiocontrol.api.services.ServicoService;
import com.ravi.desafiocontrol.api.util.ServicoUtil;
import com.ravi.desafiocontrol.api.dtos.ServicoDto;

@Service
public class ServicoServiceImpl implements ServicoService {
	
	private static final Logger log = LoggerFactory.getLogger(ServicoServiceImpl.class);
	
	ServicoUtil util = new ServicoUtil();
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Override
	public Optional<Servico> buscarPorId(Long id){
		log.info("Buscando um serviço de Id: {}", id);
		return Optional.ofNullable(servicoRepository.findServicoById(id));
	}
	
	@Override
	public Servico persistir(Servico servico) {
		log.info("Persistindo o novo servico: {}", servico);
		return this.servicoRepository.save(servico);
	}
	
	@Override
	public void remover(Servico servico) {
		log.info("Removendo da base de dados o servico: {}", servico);
		this.servicoRepository.delete(servico);
	}
	
	@Override
	public List<ServicoDto> listar(){
		log.info("Listando serviços.");
		List<Servico> listaServico = this.servicoRepository.findAll();
		List<ServicoDto> listaServicoDto = new ArrayList<ServicoDto>();
		
		for (Servico servico : listaServico) {
			listaServicoDto.add(util.converteServicoParaDto(servico));
		}
		
		return listaServicoDto;
	}
	
}
