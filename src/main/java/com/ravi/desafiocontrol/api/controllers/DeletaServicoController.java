package com.ravi.desafiocontrol.api.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.desafiocontrol.api.dtos.ServicoDto;
import com.ravi.desafiocontrol.api.entities.Servico;
import com.ravi.desafiocontrol.api.response.Response;
import com.ravi.desafiocontrol.api.services.ServicoService;
import com.ravi.desafiocontrol.api.util.ServicoUtil;

@RestController
@RequestMapping("/desafio/deletar-servico")
@CrossOrigin(origins = "*")
public class DeletaServicoController {
	
	private static final Logger log = LoggerFactory.getLogger(DeletaServicoController.class);
	
	private ServicoUtil util = new ServicoUtil();
	
	@Autowired
	private ServicoService servicoService;
	
	public DeletaServicoController() {
		
	}
	
	/**
	 * Deleta um serviço da base de dados dado um Id;
	 * 
	 * @param id
	 * @return ResponseEntity <Response<ServicoDto>>
	 */
	@DeleteMapping(value = "/id/{id}")
	public ResponseEntity <Response<ServicoDto>> deletar(@PathVariable("id") Long id) {
		Optional<Servico> servico = this.servicoService.buscarPorId(id);
		
		Response<ServicoDto> response = new Response<ServicoDto>();
		
		if (!servico.isPresent()) {
			log.info("Servico não encontrado para o id: {}", id);
			response.getErrors().add("Servico não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		log.info("Deletando serviço {}", servico.get().toString());
		
		this.servicoService.remover(servico.get());
		response.setData(util.converteServicoParaDto(servico.get()));
		
		return ResponseEntity.ok(response);
	}
}
