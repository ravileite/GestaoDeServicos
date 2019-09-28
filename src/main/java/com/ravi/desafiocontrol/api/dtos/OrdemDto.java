package com.ravi.desafiocontrol.api.dtos;

import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class OrdemDto {
	
	private Long id;
	private Long servicoId;
	private Integer quantidade;
	private String nomeFuncionario;
	private Date data;
	private String horaInicio;
	private String horaFim;
	private Optional<String> detalhe = Optional.empty();
	
	public OrdemDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull(message = "ID de serviço não pode ser vazio.")
	@Min(value = 1)
	public Long getServicoId() {
		return servicoId;
	}

	public void setServicoId(Long servicoId) {
		this.servicoId = servicoId;
	}
	
	@NotNull(message = "Quantidade não pode ser vazia.")
	@Min(value = 1, message = "Quantidade deve ser maior ou igual a 1.")
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@NotEmpty(message = "Nome do funcionário não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Nomde do funcionário deve conter entre 1 e 200 caracteres.")
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	
	@NotNull(message = "Data não pode ser vazia.")
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	@NotNull(message = "Hora início não pode ser vazia.")
	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}	
	
	@NotNull(message = "Hora fim não pode ser vazia.")
	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}
	
	public Optional<String> getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(Optional<String> detalhe) {
		this.detalhe = detalhe;
	}

	@Override
	public String toString() {
		return "OrdemDto [id=" + id + ", servicoId=" + servicoId + ", quantidade=" + quantidade + ", nomeFuncionario="
				+ nomeFuncionario + ", data=" + data + ", horaInicio=" + horaInicio + ", horaFim=" + horaFim
				+ ", detalhe=" + detalhe + "]";
	}
}
