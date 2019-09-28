package com.ravi.desafiocontrol.api.response;

import java.util.ArrayList;
import java.util.List;

import com.ravi.desafiocontrol.api.dtos.OrdemDto;
import com.ravi.desafiocontrol.api.dtos.ServicoDto;
import com.ravi.desafiocontrol.api.dtos.TabelaDto;

public class Response <T>{
	
	private T data;
	private List<String> errors;
 	
	public Response() {
		
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	@SuppressWarnings("unchecked")
	public void setDataListaServico(List<ServicoDto> lista) {
		this.data = (T) lista;
	}
	
	@SuppressWarnings("unchecked")
	public void setDataListaOrdem(List<OrdemDto> lista) {
		this.data = (T) lista;
	}
	
	@SuppressWarnings("unchecked")
	public void setDataTabela(TabelaDto tabelaDto) {
		this.data = (T) tabelaDto;
	}
	
	public List<String> getErrors(){
		if(this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}
	
	public void setErrors(List<String> errors) {
		this.errors = errors;
		}
}
