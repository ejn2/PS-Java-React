package br.com.banco.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.banco.constrollers.ContaController;
import br.com.banco.dao.ContaDAO;
import br.com.banco.models.ContaModel;
import br.com.banco.services.ContaService;
import br.com.banco.utils.TesteUtils;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ContaController.class)
public class ContaControllerTest {

	private final String API_URL = "http://localhost:8080/api/v1/conta"; 
	
	@MockBean
	private ContaService contaService;
	
	@MockBean
	private ContaDAO contaDAO;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	ContaModel conta = TesteUtils.buildConta();
	
	
	
	@Test
	void findAllContaTest() throws Exception {
		
		when(this.contaService.findAll(Mockito.any(PageRequest.class)))
			.thenReturn(new PageImpl<>(List.of(this.conta)));
			
		this.mockMvc.perform(get(this.API_URL))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.content[0].transferencia[0].dataTransferencia", is("2019-01-01T07:00:00")));
		
	}
	
}
