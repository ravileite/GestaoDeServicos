package com.ravi.desafiocontrol.api.dtos;

import java.util.ArrayList;
import java.util.List;

public class TabelaDto {
	
	List<RelatorioDto> listaRelatorio = new ArrayList<RelatorioDto>();
	TotalDto total = new TotalDto();
	
	public TotalDto getTotal() {
		return total;
	}

	public void setTotal(TotalDto total) {
		this.total = total;
	}

	public List<RelatorioDto> getListaRelatorios() {
		return listaRelatorio;
	}
	
	public void setListaRelatorios(List<RelatorioDto> listaRelatorios) {
		this.listaRelatorio = listaRelatorios;
	}

	@Override
	public String toString() {
		return "TabelaDto [listaRelatorio=" + listaRelatorio + ", totalDto=" + total + "]";
	}
	

}