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

import br.com.banco.constrollers.TransferenciaController;
import br.com.banco.dao.TransferenciaDAO;
import br.com.banco.models.TransferenciaModel;
import br.com.banco.services.TransferenciaService;
import br.com.banco.utils.TesteUtils;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = TransferenciaController.class)
public class TransferenciaControllerTest {
	
	private final String API_URL = "http://localhost:8080/api/v1/transferencia"; 
	
	@MockBean
	private TransferenciaService transferenciaService;
	
	
	@MockBean
	private TransferenciaDAO transferenciaDAO;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	TransferenciaModel transferenciaModel = TesteUtils.buildTransferencia();

	
	// ==================== [ FIND ALL ] ====================
	
	@Test
	void findAllTransferenciaTest() throws Exception {
		
		when(this.transferenciaService.findAll(Mockito.any(PageRequest.class)))
			.thenReturn(new PageImpl<>(List.of(this.transferenciaModel)));
		
		this.mockMvc.perform(get(this.API_URL))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.content[0].dataTransferencia", is("2019-01-01T07:00:00")))
			.andExpect(MockMvcResultMatchers.jsonPath("$.content[0].tipo", is("TRANSFERENCIA")));
		
		
		
	}
	
	
	
}
