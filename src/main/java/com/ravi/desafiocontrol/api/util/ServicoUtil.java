package com.ravi.desafiocontrol.api.util;

import com.ravi.desafiocontrol.api.dtos.ServicoDto;
import com.ravi.desafiocontrol.api.entities.Servico;

public class ServicoUtil {
	
	public ServicoUtil() {
		
	}
	
	/**
	 * Converte Servico para ServicoDto.
	 * 
	 * @param servico
	 * @return ServicoDto
	 */
	public ServicoDto converteServicoParaDto(Servico servico) {
		ServicoDto servicoDto = new ServicoDto();
		servicoDto.setId(servico.getId());
		servicoDto.setDescricao(servico.getDescricao());
		servicoDto.setValor(servico.getValor());
		
		return servicoDto;
	}	
	
	/**
	 * Converte um ServicoDto para Servico.
	 * 
	 * @param servico
	 * @param servicoDto
	 * @return Servico
	 */
	public Servico converteDtoParaServico(Servico servico, ServicoDto servicoDto) {
		servico.setDescricao(servicoDto.getDescricao());
		servico.setValor(servicoDto.getValor());
		
		return servico;
	}
}
