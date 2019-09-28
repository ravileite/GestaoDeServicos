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

import com.ravi.desafiocontrol.api.entities.Ordem;
import com.ravi.desafiocontrol.api.repositories.OrdemRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrdemServiceTest {
	
	@MockBean
	private OrdemRepository ordemRepository;
	
	@Autowired
	private OrdemService ordemService;
	
	private static final Long ID = 1L;
	
	@Before
	public void setUp() {
		BDDMockito.given(this.ordemRepository.findOrdemById(Mockito.anyLong())).willReturn(new Ordem());
		BDDMockito.given(this.ordemRepository.save(Mockito.any(Ordem.class))).willReturn(new Ordem());
	}
	
	@Test
	public void testBuscarOrdemPorId() {
		Optional<Ordem> ordem = this.ordemService.buscarPorId(ID);
		
		assertTrue(ordem.isPresent());
	}
	
	@Test
	public void testPersistirOrdem() {
		Ordem ordem = this.ordemService.persistir(new Ordem());
		
		assertNotNull(ordem);
	}
	
	@Test
	public void testRemorverOrdem() {
		Ordem ordem = this.ordemService.persistir(new Ordem());
		ordemService.remover(ordem);
		
		verify(this.ordemRepository, times(1)).delete(ordem);
	}
	
}
