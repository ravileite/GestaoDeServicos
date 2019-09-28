package com.ravi.desafiocontrol.api.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.desafiocontrol.api.services.OrdemService;
import com.ravi.desafiocontrol.api.dtos.OrdemDto;
import com.ravi.desafiocontrol.api.response.Response;

@RestController
@RequestMapping("/desafio/listar-ordens")
@CrossOrigin(origins = "*")
public class ListaOrdensController {
	
	private static final Logger log = LoggerFactory.getLogger(ListaOrdensController.class);
	
	@Autowired
	private OrdemService ordemService;
	
	public ListaOrdensController() {
		
	}
	
	/**
	 * Lista todas as ordens de seviço cadastradas na base de dados.
	 * 
	 * @return ResponseEntity<Response<OrdemDto>>
	 */
	@GetMapping
	public ResponseEntity<Response<OrdemDto>> listar(){
		log.info("Listando ordens de serviço...");
		
		Response<OrdemDto> response = new Response<OrdemDto>();
		
		List<OrdemDto> listaOrdemDto = this.ordemService.listar();
		
		if(listaOrdemDto.size() == 0) {
			log.info("Não há ordens de serviço para serem listadas.");
			response.getErrors().add("Não há ordem de serviço para serem listadas.");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setDataListaOrdem(listaOrdemDto);
		
		return ResponseEntity.ok(response);
	}
}
