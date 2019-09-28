package com.ravi.desafiocontrol.api.dtos;

import java.math.BigDecimal;

public class TotalDto {
	
	private BigDecimal valorTotal;
	private BigDecimal valorFinal;
	
	public TotalDto() {
		
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	@Override
	public String toString() {
		return "TotalDto [valorTotal=" + valorTotal + ", valorFinal=" + valorFinal + "]";
	}	
}
