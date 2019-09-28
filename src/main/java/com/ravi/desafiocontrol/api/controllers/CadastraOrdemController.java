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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.desafiocontrol.api.dtos.OrdemDto;
import com.ravi.desafiocontrol.api.entities.Ordem;
import com.ravi.desafiocontrol.api.entities.Servico;
import com.ravi.desafiocontrol.api.response.Response;
import com.ravi.desafiocontrol.api.services.OrdemService;
import com.ravi.desafiocontrol.api.services.ServicoService;
import com.ravi.desafiocontrol.api.util.OrdemUtil;

@RestController
@RequestMapping("/desafio/cadastrar-ordem")
@CrossOrigin(origins = "*")
public class CadastraOrdemController {
	
	private static final Logger log = LoggerFactory.getLogger(CadastroServicoController.class);
	
	OrdemUtil util = new OrdemUtil();
	
	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private OrdemService ordemService;
	
	public CadastraOrdemController() {
		
	}	
	
	/**
	 * Cadastra uma ordem de serviço na base de dados. 
	 * 
	 * @param ordemDto
	 * @param result
	 * @return ResponseEntity<Response<OrdemDto>>
	 */
	@PostMapping
	public ResponseEntity<Response<OrdemDto>> cadastra(@Valid @RequestBody OrdemDto ordemDto, 
			BindingResult result) {
			log.info("Cadastrando ordem {}", ordemDto.toString());
			Response<OrdemDto> response = new Response<OrdemDto>();
			Ordem ordem = new Ordem();
			
			this.verificaServicoId(ordemDto, result);
			
			util.converteDtoParaOrdem(ordemDto, ordem);
			
			if(result.hasErrors()) {
				log.error("Erro ao cadastrar Ordem: {}", result.getAllErrors());
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}

			Optional<Servico> servico = this.servicoService.buscarPorId(ordemDto.getServicoId());
			servico.ifPresent(serv -> ordem.setServico(serv));
			this.ordemService.persistir(ordem);
		
			response.setData(util.converteOrdemParaDto(ordem));
			return ResponseEntity.ok(response);
	}
	
	/**
	 * Verifica se o serviço da ordem passada está presente na base de dados.
	 * 
	 * @param ordemDto
	 * @param result
	 */
	private void verificaServicoId(OrdemDto ordemDto, BindingResult result) {
		Optional<Servico> servico = this.servicoService.buscarPorId(ordemDto.getServicoId());
		if(!servico.isPresent()) {
			result.addError(new ObjectError("servico", "Servico não cadastrado."));
		}
	}
}
