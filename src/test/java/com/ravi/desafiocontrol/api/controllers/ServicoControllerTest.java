package com.ravi.desafiocontrol.api.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ravi.desafiocontrol.api.dtos.ServicoDto;
import com.ravi.desafiocontrol.api.entities.Servico;
import com.ravi.desafiocontrol.api.services.ServicoService;
import com.ravi.desafiocontrol.api.util.ServicoUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ServicoControllerTest {
	
	private MockMvc mvc;

	@Autowired

	private WebApplicationContext context;


	@Before
	public void setUp() {

	mvc = MockMvcBuilders.webAppContextSetup(context).build();

	}
	
	@MockBean
	private ServicoService servicoService;
	
	
	private static final String CADASTRA_SERVICO_URL = "/desafio/cadastrar-servico/";
	private static final String DELETA_SERVICO_URL = "/desafio/deletar-servico/id/";
	private static final String ATUALIZA_SERVICO_URL = "/desafio/atualizar-servico/id/";
	private static final String DESCRICAO = "ABC";
	private static final Long ID = 1L;
	private static final BigDecimal VALOR = new BigDecimal(50.0);
	private static final String NOVA_DESCRICAO = "CBA";
	private static final BigDecimal NOVO_VALOR = new BigDecimal(100.0);
	
	ServicoUtil util = new ServicoUtil();
	
	@Test
	@WithMockUser
	public void testCadastrarServico()throws Exception {
		Servico servico = this.obterDadosServio();
		BDDMockito.given(this.servicoService.persistir(Mockito.any(Servico.class))).willReturn(servico);
		mvc.perform(MockMvcRequestBuilders.post(CADASTRA_SERVICO_URL)
				.content(this.obterJsonRequisicaoPost(DESCRICAO, VALOR))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.descricao").value(DESCRICAO))
				.andExpect(jsonPath("$.data.valor").value(VALOR))
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	@Test
	@WithMockUser
	public void testAtualizarServico() throws Exception{
		BDDMockito.given(this.servicoService.buscarPorId(Mockito.anyLong())).willReturn(Optional.of(new Servico()));
		
		mvc.perform(MockMvcRequestBuilders.put(ATUALIZA_SERVICO_URL + ID)
				.content(this.obterJsonRequisicaoPost(NOVA_DESCRICAO, NOVO_VALOR))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.descricao").value(NOVA_DESCRICAO))
				.andExpect(jsonPath("$.data.valor").value(NOVO_VALOR))
				.andExpect(jsonPath("$.errors").isEmpty());
	
	}
	
	@Test
	@WithMockUser
	public void testRemoverServico() throws Exception{
		BDDMockito.given(this.servicoService.buscarPorId(Mockito.anyLong())).willReturn(Optional.of(new Servico()));
		
		mvc.perform(MockMvcRequestBuilders.delete(DELETA_SERVICO_URL + ID)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	private String obterJsonRequisicaoPost(String descricao, BigDecimal valor) throws JsonProcessingException {
		ServicoDto servicoDto = new ServicoDto();
		servicoDto.setId(null);
		servicoDto.setDescricao(descricao);
		servicoDto.setValor(valor);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(servicoDto);
	}
	
	private Servico obterDadosServio() {
		Servico servico = new Servico();
		servico.setId(ID);
		servico.setDescricao(DESCRICAO);
		servico.setValor(VALOR);
		return servico;
	}
	
}
