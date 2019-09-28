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

import com.ravi.desafiocontrol.api.entities.Ordem;
import com.ravi.desafiocontrol.api.response.Response;
import com.ravi.desafiocontrol.api.services.OrdemService;
import com.ravi.desafiocontrol.api.util.OrdemUtil;
import com.ravi.desafiocontrol.api.dtos.OrdemDto;

@RestController
@RequestMapping("/desafio/atualizar-ordem")
@CrossOrigin(origins = "*")
public class AtualizaOrdemController {

	private static final Logger log = LoggerFactory.getLogger(AtualizaOrdemController.class);
	
	@Autowired
	private OrdemService ordemService;
	
	OrdemUtil util = new OrdemUtil();
	
	public AtualizaOrdemController() {
		
	}
	
	/**
	 * Atualiza uma ordem de serviço cadastrado na base de dados dado um ID.
	 * 
	 * @param id
	 * @param ordemDto
	 * @param result
	 * @return ResponseEntity<Response<OrdemDto>>
	 */
	@PutMapping(value = "/id/{id}")
	public ResponseEntity<Response<OrdemDto>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody OrdemDto ordemDto, BindingResult result){
		log.info("Atualizando ordem de serviço {}", ordemDto.toString());
		Response<OrdemDto> response = new Response<OrdemDto>();
		
		Optional<Ordem> ordem = this.ordemService.buscarPorId(id);
		
		if(!ordem.isPresent()) {
			result.addError(new ObjectError("ordem", "Ordem de serviço não encontrada."));
		}
		
		if(result.hasErrors()) {
			log.error("Erro validando serviço {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		util.converteDtoParaOrdem(ordemDto, ordem.get());
		
		this.ordemService.persistir(ordem.get());
		
		response.setData(ordemDto);
		return ResponseEntity.ok(response);
	}
}
