package com.ravi.desafiocontrol.api.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class ServicoDto {
	
	private Long id;
	private String descricao;
	private BigDecimal valor;
	
	public ServicoDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Descrição não pode ser vazia.")
	@Length(min = 1, max = 150, message = "Descrição deve conter entre 1 e 150 caracteres.")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@NotNull(message = "Valor não pode ser nulo.")
	@DecimalMin(value = "0.00", inclusive = true) 
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
