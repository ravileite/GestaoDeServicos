package com.ravi.desafiocontrol.api.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class RelatorioDto {
	
	private String servico;
	private BigDecimal valorUnitario;
	private Integer quantidade;
	private BigDecimal valorTotal;
	private BigDecimal desconto;
	private BigDecimal valorFinal;
	
	public RelatorioDto() {
		
	}
	
	@NotEmpty(message = "Servico não pode ser vazio.")
	@Length(min = 1, max = 150, message = "Servico deve conter entre 1 e 150 caracteres.")
	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	@NotNull(message = "Valor unitário não pode ser nulo.")
	@DecimalMin(value = "0.00", inclusive = true) 
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	@NotNull(message = "Quantidade não pode ser vazia.")
	@Min(value = 1, message = "Quantidade deve ser maior ou igual a 1.")
	public Integer getQuatidade() {
		return quantidade;
	}

	public void setQuatidade(Integer quatidade) {
		this.quantidade = quatidade;
	}

	@NotNull(message = "Valor total não pode ser nulo.")
	@DecimalMin(value = "0.00", inclusive = true) 
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	@NotNull(message = "Desconto não pode ser vazio.")
	@DecimalMin(value = "0.00", inclusive = true)
	@DecimalMax(value = "0.30", inclusive = true)
	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	@NotNull(message = "Valor final não pode ser nulo.")
	@DecimalMin(value = "0.00", inclusive = true) 
	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	@Override
	public String toString() {
		return "RelatorioDto [servico=" + servico + ", valorUnitario=" + valorUnitario + ", quatidade=" + quantidade
				+ ", valoTotal=" + valorTotal + ", desconto=" + desconto + ", valorFinal=" + valorFinal + "]";
	}	
}
