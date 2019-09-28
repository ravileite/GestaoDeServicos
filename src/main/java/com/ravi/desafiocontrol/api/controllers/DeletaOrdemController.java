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

import com.ravi.desafiocontrol.api.dtos.OrdemDto;
import com.ravi.desafiocontrol.api.entities.Ordem;
import com.ravi.desafiocontrol.api.response.Response;
import com.ravi.desafiocontrol.api.services.OrdemService;
import com.ravi.desafiocontrol.api.util.OrdemUtil;

@RestController
@RequestMapping("/desafio/deletar-ordem")
@CrossOrigin(origins = "*")
public class DeletaOrdemController {
	
	private static final Logger log = LoggerFactory.getLogger(DeletaOrdemController.class);
	
	OrdemUtil util = new OrdemUtil();
	
	@Autowired
	private OrdemService ordemService;
	
	public DeletaOrdemController() {
		
	}
	
	/**
	 * Deleta uma ordem de serviço da base de dados dado um Id.
	 * 
	 * @param id
	 * @return ResponseEntity <Response<OrdemDto>>
	 */
	@DeleteMapping(value = "/id/{id}")
	public ResponseEntity <Response<OrdemDto>> deletar(@PathVariable("id") Long id) {
		Optional<Ordem> ordem = this.ordemService.buscarPorId(id);
		
		Response<OrdemDto> response = new Response<OrdemDto>();
		
		if (!ordem.isPresent()) {
			log.info("Ordem não encontrado para o id: {}", id);
			response.getErrors().add("Ordem de serviço não encontrada para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		log.info("Deletando ordem {}", ordem.get().toString());
		
		this.ordemService.remover(ordem.get());
		response.setData(util.converteOrdemParaDto(ordem.get()));
		
		return ResponseEntity.ok(response);
	}
}
