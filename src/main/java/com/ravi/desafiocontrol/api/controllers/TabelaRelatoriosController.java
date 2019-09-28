package com.ravi.desafiocontrol.api.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.desafiocontrol.api.dtos.OrdemDto;
import com.ravi.desafiocontrol.api.dtos.RelatorioDto;
import com.ravi.desafiocontrol.api.dtos.TabelaDto;
import com.ravi.desafiocontrol.api.dtos.TotalDto;
import com.ravi.desafiocontrol.api.entities.Servico;
import com.ravi.desafiocontrol.api.response.Response;
import com.ravi.desafiocontrol.api.services.OrdemService;
import com.ravi.desafiocontrol.api.services.ServicoService;

@RestController
@RequestMapping("/desafio/gerar-relatorios")
@CrossOrigin(origins = "*")
public class TabelaRelatoriosController {
	
	private static final Logger log = LoggerFactory.getLogger(TabelaRelatoriosController.class);
	
	@Autowired
	private OrdemService ordemService;
	
	@Autowired
	private ServicoService servicoService;
	
	public TabelaRelatoriosController() {
		
	}
	
	/**
	 * Gera uma tabela contendo todos os relatorios referentes a cada serviço.
	 * 
	 * @return ResponseEntity<Response<TabelaDto>>
	 */
	@GetMapping
	public ResponseEntity<Response<TabelaDto>> gerarTabela(){
		log.info("Gerando tabela de relaróios. ");
		
		Response<TabelaDto> response = new Response<TabelaDto>();
		
		List<OrdemDto> listaOrdemDto = this.ordemService.listar();
		
		if(listaOrdemDto.size() == 0) {
			log.info("Não há ordens para gerar relatórios.");
			response.getErrors().add("Não há ordens para gerar relatórios.");
			return ResponseEntity.badRequest().body(response);
		}
		
		List<RelatorioDto> listaRelatorioDto = this.geraRelatorios(listaOrdemDto);
		TabelaDto tabelaDto = new TabelaDto();
		tabelaDto.setListaRelatorios(listaRelatorioDto);
		tabelaDto.setTotal(this.calculaTotais(listaRelatorioDto));
		
		response.setDataTabela(tabelaDto);
		return ResponseEntity.ok(response);
	}
	
	private List<RelatorioDto> geraRelatorios(List<OrdemDto> listaOrdemDto){
		List<RelatorioDto> listaRelatorio = new ArrayList<RelatorioDto>();
		
		for (OrdemDto ordemDto : listaOrdemDto) {
			int index = this.buscaRelatorio(ordemDto, listaRelatorio);
			Optional<Servico> servico = this.servicoService.buscarPorId(ordemDto.getServicoId());
			if(index != -1) {
				listaRelatorio.get(index).setServico(servico.get().getDescricao());
				listaRelatorio.get(index).setValorUnitario(servico.get().getValor());
				listaRelatorio.get(index).setQuatidade(listaRelatorio.get(index).getQuatidade() + ordemDto.getQuantidade());
				listaRelatorio.get(index).setValorTotal(listaRelatorio.get(index).getValorTotal().add(  
						(servico.get().getValor().multiply(BigDecimal.valueOf(ordemDto.getQuantidade())))));
				listaRelatorio.get(index).setDesconto(this.calculaDesconto(listaRelatorio.get(index).getQuatidade()));
				listaRelatorio.get(index).setValorFinal(listaRelatorio.get(index).getValorTotal().subtract(
						listaRelatorio.get(index).getValorTotal().multiply(
						listaRelatorio.get(index).getDesconto())));;
			}else {
				RelatorioDto relatorioDto = new RelatorioDto();
				relatorioDto.setServico(servico.get().getDescricao());
				relatorioDto.setQuatidade(ordemDto.getQuantidade());
				relatorioDto.setValorUnitario(servico.get().getValor());
				relatorioDto.setDesconto(this.calculaDesconto(relatorioDto.getQuatidade()));
				relatorioDto.setValorTotal(relatorioDto.getValorUnitario().multiply(BigDecimal.valueOf(relatorioDto.getQuatidade())));
				relatorioDto.setValorFinal(relatorioDto.getValorTotal().subtract(
						(relatorioDto.getValorTotal().multiply(relatorioDto.getDesconto()))));
				listaRelatorio.add(relatorioDto);	
			}
		}
		
		return listaRelatorio;
	}
	
	private int buscaRelatorio(OrdemDto ordemDto, List<RelatorioDto> listaRelatorio) {
		for (RelatorioDto relatorioDto : listaRelatorio) {
			Optional<Servico> servico = this.servicoService.buscarPorId(ordemDto.getServicoId());
			String descricao = servico.get().getDescricao();
			if(relatorioDto.getServico().equals(descricao)) {
				return listaRelatorio.indexOf(relatorioDto);
			}
		}
		return -1;
	}
	
	private BigDecimal calculaDesconto(Integer quantidade) {
		if(quantidade >=10 && quantidade < 20) {
			return new BigDecimal("0.1");
		}else if (quantidade >=20 && quantidade <30) {
			return new BigDecimal("0.2");
		}else if(quantidade >= 30) {
			return new BigDecimal("0.3");
		}
		return new BigDecimal("0.0");
	}
	
	private TotalDto calculaTotais(List<RelatorioDto> listaRelatorioDto){
		TotalDto totalDto = new TotalDto();
		BigDecimal valorTotal = new BigDecimal("0.0");
		BigDecimal valorFinal = new BigDecimal("0.0");
		
		for (RelatorioDto relatorioDto : listaRelatorioDto) {
			valorTotal = valorTotal.add(relatorioDto.getValorTotal());
			valorFinal = valorFinal.add(relatorioDto.getValorFinal());
			System.out.println(valorFinal);
			System.out.println(valorTotal);
		}
		
		totalDto.setValorFinal(valorFinal);
		totalDto.setValorTotal(valorTotal);
		
		return totalDto;
	}
	
}
