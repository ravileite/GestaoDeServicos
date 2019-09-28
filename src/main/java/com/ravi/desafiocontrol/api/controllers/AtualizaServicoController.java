package com.ravi.desafiocontrol.api.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.desafiocontrol.api.dtos.ServicoDto;
import com.ravi.desafiocontrol.api.entities.Servico;
import com.ravi.desafiocontrol.api.response.Response;
import com.ravi.desafiocontrol.api.services.ServicoService;
import com.ravi.desafiocontrol.api.util.ServicoUtil;

@RestController
@RequestMapping("/desafio/atualizar-servico")
@CrossOrigin(origins = "*")
public class AtualizaServicoController {
	
	private static final Logger log = LoggerFactory.getLogger(AtualizaServicoController.class);
	
	private ServicoUtil util = new ServicoUtil();
	
	@Autowired
	private ServicoService servicoService;
	
	public AtualizaServicoController() {
		
	}
	
	/**
	 * Atualiza um serviço cadastrado na base de dados dado um ID.
	 * 
	 * @param id
	 * @param servicoDto
	 * @param result
	 * @return ResponseEntity <Response<ServicoDto>>
	 */
	
	@PutMapping(value = "/id/{id}")
	public ResponseEntity <Response<ServicoDto>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody ServicoDto servicoDto, BindingResult result) {
		
		log.info("Atualizando serviço {}", servicoDto,toString());
		Response<ServicoDto> response = new Response<ServicoDto>();
		
		Optional<Servico> servico = this.servicoService.buscarPorId(id);
		
		if(!servico.isPresent()) {
			result.addError(new ObjectError("servico", "Serviço não encontrado."));
		}
		
		if(result.hasErrors()) {
			log.error("Erro validando serviço {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		util.converteDtoParaServico(servico.get(), servicoDto);
		
		try {
			this.servicoService.persistir(servico.get());
		} catch (Exception E) {
			response.getErrors().add("Descrição já cadastrada na base de dados.");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(util.converteServicoParaDto(servico.get()));
		
		return ResponseEntity.ok(response);
	}
}
