package com.ravi.desafiocontrol.api.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.desafiocontrol.api.dtos.ServicoDto;
import com.ravi.desafiocontrol.api.entities.Servico;
import com.ravi.desafiocontrol.api.response.Response;
import com.ravi.desafiocontrol.api.services.ServicoService;
import com.ravi.desafiocontrol.api.util.ServicoUtil;


@RestController
@RequestMapping("/desafio/cadastrar-servico")
@CrossOrigin(origins = "*")
public class CadastroServicoController {
	
	private static final Logger log = LoggerFactory.getLogger(CadastroServicoController.class);
	
	ServicoUtil util = new ServicoUtil();
	
	@Autowired
	private ServicoService servicoService;
	
	public CadastroServicoController() {
		
	}
	
	/**
	 * Cadastra um serviço no sistema.
	 * @param servicoDto
	 * @param result
	 * @return ResponseEntity<Response<ServicoDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<ServicoDto>> cadastrar(@Valid @RequestBody ServicoDto servicoDto, 
			BindingResult result) {
		
		log.info("Cadastrando Servico {} ", servicoDto.toString());
		Response<ServicoDto> response = new Response<ServicoDto>();
		Servico servico = new Servico();
		
		util.converteDtoParaServico(servico, servicoDto);
		
		if(result.hasErrors()) {
			log.error("Erro ao cadastrar serviço: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			this.servicoService.persistir(servico);
		} catch (Exception E) {
			response.getErrors().add("Descricao já cadastrada na base de dados.");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(util.converteServicoParaDto(servico));
		return ResponseEntity.ok(response);
		
	}
	
}
