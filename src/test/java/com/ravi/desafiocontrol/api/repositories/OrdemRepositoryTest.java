package com.ravi.desafiocontrol.api.repositories;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ravi.desafiocontrol.api.entities.Ordem;
import com.ravi.desafiocontrol.api.entities.Servico;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrdemRepositoryTest {
	
	@Autowired
	private OrdemRepository ordemRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	private static final Long ID = 1L;
	
	@Before
	public void setUp() throws Exception{
		Servico servico = servicoRepository.save(obterDadosServico());
		this.ordemRepository.save(obterDadosOrdem(servico));
	}
	
	@After
	public final void tearDown() {
		this.ordemRepository.deleteAll();
		this.servicoRepository.deleteAll();
	}
	
	@Test
	public void testBuscarPorId() {
		Ordem ordem = ordemRepository.findOrdemById(ID);
		
		assertEquals(ID, ordem.getId());
	}
	
	private Servico obterDadosServico() {
		Servico servico = new Servico();
		servico.setDescricao("Serviço A.");
		servico.setValor(new BigDecimal(50));
		
		return servico;
	}
	
	private Ordem obterDadosOrdem(Servico servico) {
		Ordem ordem = new Ordem();
		ordem.setServico(servico);
		ordem.setQuantidade(5);
		ordem.setNomeFuncionario("José");
		ordem.setData(new Date(2019-10-10));
		ordem.setHoraInicio("16:00:00");
		ordem.setHoraFim("17:00:00");
		
		return ordem;
	}
}
