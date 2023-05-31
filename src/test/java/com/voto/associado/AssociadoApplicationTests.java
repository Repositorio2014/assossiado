package com.voto.associado;

import com.voto.associado.controller.PautaController;
import com.voto.associado.controller.VotacaoController;
import com.voto.associado.service.PautaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
public class AssociadoApplicationTests {

	private MockMvc mockMvc;
	private MockMvc mockMvcVotacao;
	private MockMvc mockMvcVoto;

	@Mock
	private PautaService service;

	@InjectMocks
	private PautaController controller;

	@InjectMocks
	private VotacaoController votacaoController;

	@InjectMocks
	private VotacaoController votoController;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockMvcVotacao = MockMvcBuilders.standaloneSetup(votacaoController).build();
		mockMvcVoto = MockMvcBuilders.standaloneSetup(votoController).build();
	}

	@Test
	public void getPautas() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/pauta/").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void criarPauta() throws Exception {
		String json = "{\n" +
				"  \"pauta\": \"pautaC\"\n" +
				"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/pauta/criarPauta/").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void iniciarVotacao() throws Exception {
		mockMvcVotacao.perform(MockMvcRequestBuilders.post("/votacao/1/iniciar-votacao/").contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void votar() throws Exception {
		String json = "{\"cpf\": \"122155\",\n" +
				"  \"mensagem\": \"SIM\"\n" +
				"}";
		mockMvcVoto.perform(MockMvcRequestBuilders.post("/voto/2/votar/").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getPauta() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/pauta/", 2).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
