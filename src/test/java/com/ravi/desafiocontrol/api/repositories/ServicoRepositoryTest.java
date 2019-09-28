package com.ravi.desafiocontrol.api.repositories;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ravi.desafiocontrol.api.entities.Servico;
import com.ravi.desafiocontrol.api.repositories.ServicoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ServicoRepositoryTest {

	@Autowired
	private ServicoRepository servicoRepository;
	
	private static final Long ID = 1L;
			
	@Before
	public void setUp() throws Exception{
		Servico servico = new Servico();
		servico.setDescricao("Servico A");
		servico.setValor(new BigDecimal(50));
		this.servicoRepository.save(servico);
	}
	
	@After
	public final void tearDown() {
		this.servicoRepository.deleteAll();
	}
	
	@Test
	public void testBuscarPorId() {
		Servico servico = this.servicoRepository.findServicoById(ID);
		
		assertEquals(ID, servico.getId());
	}
}
