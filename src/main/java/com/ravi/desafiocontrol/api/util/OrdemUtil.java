package com.ravi.desafiocontrol.api.util;

import java.util.Optional;

import com.ravi.desafiocontrol.api.dtos.OrdemDto;
import com.ravi.desafiocontrol.api.entities.Ordem;

public class OrdemUtil {
	
	public OrdemUtil() {
		
	}
	
	public OrdemDto converteOrdemParaDto(Ordem ordem) {
		OrdemDto ordemDto = new OrdemDto();
		ordemDto.setData(ordem.getData());
		ordemDto.setHoraFim(ordem.getHoraFim());
		ordemDto.setHoraInicio(ordem.getHoraInicio());
		ordemDto.setNomeFuncionario(ordem.getNomeFuncionario());
		ordemDto.setQuantidade(ordem.getQuantidade());
		ordemDto.setId(ordem.getId());
		ordemDto.setServicoId(ordem.getServico().getId());
		ordem.getDetalheOpt().ifPresent(detalhe -> ordemDto.setDetalhe(Optional.of(detalhe.toString())));
		
		return ordemDto;
	}
	
	public Ordem converteDtoParaOrdem(OrdemDto ordemDto, Ordem ordem) {
		ordem.setData(ordemDto.getData());
		ordem.setHoraFim(ordemDto.getHoraFim());
		ordem.setHoraInicio(ordemDto.getHoraInicio());
		ordem.setNomeFuncionario(ordemDto.getNomeFuncionario());
		ordem.setQuantidade(ordemDto.getQuantidade());
		ordemDto.getDetalhe().ifPresent(det -> ordem.setDetalhe(det));
		return ordem;
	}
}
