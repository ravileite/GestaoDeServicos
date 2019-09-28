package com.ravi.desafiocontrol.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name = "ordem")
public class Ordem implements Serializable{
	
	private static final long serialVersionUID = -5754246207015712518L;
	
	private Long id;
	private Servico servico;
	private Integer quantidade;
	private String nomeFuncionario;
	private Date data;
	private String horaInicio;
	private String horaFim;
	private String detalhe;
	
	public Ordem() {
		
	}
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	@Column(name = "quantidade", nullable = false)
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@Column(name = "nome_funcionario", nullable = false)
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	@Column(name = "data", nullable = false)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column(name = "hora_inicio", nullable = false)
	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	@Column(name = "hora_fim", nullable = false)
	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	@Column(name = "detalhe", nullable = true)
	public String getDetalhe() {
		return detalhe;
	}
	
	@Transient
	public Optional<String> getDetalheOpt(){
		return Optional.ofNullable(detalhe);
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	@Override
	public String toString() {
		return "Ordem [id=" + id + ", servico=" + servico + ", quantidade=" + quantidade + ", nomeFuncionario="
				+ nomeFuncionario + ", data=" + data + ", horaInicio=" + horaInicio + ", horaFim=" + horaFim
				+ ", detalhe=" + detalhe + "]";
	}
}
