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

import com.ravi.desafiocontrol.api.dtos.ServicoDto;
import com.ravi.desafiocontrol.api.response.Response;
import com.ravi.desafiocontrol.api.services.ServicoService;

@RestController
@RequestMapping("/desafio/listar-servicos")
@CrossOrigin(origins = "*")
public class ListaServicosController {
	
	private static final Logger log = LoggerFactory.getLogger(ListaServicosController.class);
	
	@Autowired
	private ServicoService servicoService;
	
	
	public ListaServicosController() {
		
	}
	
	/**
	 * Lista todos os serviços cadastrados na base de dados.
	 * @return ResponseEntity <Response<ServicoDto>> 
	 */
	@GetMapping
	public ResponseEntity <Response<ServicoDto>> listar(){
		log.info("Listando serviços...");
		
		Response<ServicoDto> response = new Response<ServicoDto>();
		
		List<ServicoDto> listaServicoDto = this.servicoService.listar();
		
		if(listaServicoDto.size() == 0) {
			log.info("Não há servicos para serem listados.");
			response.getErrors().add("Não há servicos para serem listados.");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setDataListaServico(listaServicoDto);
		
		return ResponseEntity.ok(response);
	}
	
}
