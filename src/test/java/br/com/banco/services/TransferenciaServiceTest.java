package br.com.banco.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

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

import br.com.banco.dao.TransferenciaDAO;
import br.com.banco.exceptions.BancoNotFoundException;
import br.com.banco.models.TransferenciaModel;
import br.com.banco.utils.TesteUtils;

@ExtendWith(MockitoExtension.class)
public class TransferenciaServiceTest {

	
	@Mock
	private TransferenciaDAO transferenciaDAO;
	
	
	@InjectMocks
	private TransferenciaService transferenciaService;
	
	
	private TransferenciaModel transferenciaModel = TesteUtils.buildTransferencia();
	
	
	
	// ==================== [ FIND ALL - TEST ] ==================== 
	
	@Test
	void whenTheMethodFindAllIsCalled_ThenAListOfContaIsReturned() {
		
		Pageable pageable = PageRequest.of(0, 3);
		
		when(this.transferenciaDAO.findAll(Mockito.any(PageRequest.class)))
			.thenReturn(new PageImpl<>(List.of(this.transferenciaModel)));
		
		Page<TransferenciaModel> listOfTransf = this.transferenciaService.findAll(pageable);
		
		
		assertEquals(this.transferenciaModel.getDataTransferencia(),
				listOfTransf.getContent().get(0).getDataTransferencia());
		
		assertEquals(this.transferenciaModel.getValor(),
				listOfTransf.getContent().get(0).getValor());
		
	}
	
	
	
	
	
	// ==================== [ FIND BY NAME- TEST ] ==================== 
	

	@Test
	void whenTheMethodFindByNameIsCalledWithValidNomeOperador_ThenATransferenciaIsReturned() throws BancoNotFoundException {
		
		when(this.transferenciaDAO.findByNomeOperadorTransacao(Mockito.anyString()))
			.thenReturn(Optional.of(this.transferenciaModel));
		
		TransferenciaModel transf = transferenciaService.findByName(this.transferenciaModel.getNomeOperadorTransacao());
		
		assertEquals(this.transferenciaModel.getNomeOperadorTransacao(),
				transf.getNomeOperadorTransacao()
				);
	}
	
	
	
	@Test
	void whenTheMethodFindByNameIsCalledWithInvalidNomeOperador_ThenAnExceptionIsThrown() throws BancoNotFoundException {
		
		when(this.transferenciaDAO.findByNomeOperadorTransacao(Mockito.anyString()))
			.thenReturn(Optional.empty());
		
		assertThrows(BancoNotFoundException.class,
				() -> transferenciaService.findByName(this.transferenciaModel.getNomeOperadorTransacao())
				);
		
	}
	
}
