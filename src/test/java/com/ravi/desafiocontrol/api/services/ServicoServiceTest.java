package com.ravi.desafiocontrol.api.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ravi.desafiocontrol.api.entities.Servico;
import com.ravi.desafiocontrol.api.repositories.ServicoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ServicoServiceTest {
	
	@MockBean
	private ServicoRepository servicoRepository;
	
	@Autowired
	private ServicoService servicoService;
	
	private static final Long ID = 1L;
	
	@Before
	public void setUp() {
		BDDMockito.given(this.servicoRepository.findServicoById(Mockito.anyLong())).willReturn(new Servico());
		BDDMockito.given(this.servicoRepository.save(Mockito.any(Servico.class))).willReturn(new Servico());
	}
	
	@Test
	public void testBuscarServicoPorId() {
		Optional<Servico> servico = this.servicoService.buscarPorId(ID);
		
		assertTrue(servico.isPresent());
	}
	
	@Test 
	public void testPersistirServico() {
		Servico servico = this.servicoService.persistir(new Servico());
		
		assertNotNull(servico);
	}
	
	
	@Test
	public void testRemoverServico() {
		Servico servico = this.servicoService.persistir(new Servico());
		servicoService.remover(servico);
		verify(this.servicoRepository, times(1)).delete(servico);
	}

}
