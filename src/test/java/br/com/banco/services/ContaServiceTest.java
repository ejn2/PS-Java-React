package br.com.banco.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.banco.dao.ContaDAO;
import br.com.banco.models.ContaModel;
import br.com.banco.utils.TesteUtils;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {
	
	@Mock
	private ContaDAO contaDAO;
	
	@InjectMocks
	private ContaService contaService;
	
	ContaModel conta = TesteUtils.buildConta();
	
	
	
	// ==================== [ FID ALL - TEST ] ==================== 
	
	@Test
	void whenTheMethodFindAllIsCalled_ThenAListOfContaIsReturned() {
		
		when(this.contaDAO.findAll(Mockito.any(PageRequest.class)))
			.thenReturn(new PageImpl<>(List.of(this.conta)));
		
		Pageable pageable = PageRequest.of(0, 3, Sort.by("name"));
		
		Page<ContaModel> listOfConta = this.contaService.findAll(pageable);
		
		
		assertEquals(this.conta.getIdConta(), listOfConta.getContent().get(0).getIdConta());
		
		assertEquals(1, listOfConta.getTotalPages());
		
	}

}
