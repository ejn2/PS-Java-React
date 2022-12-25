package br.com.banco.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.banco.dao.ContaDAO;
import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.exceptions.BancoNotFoundException;
import br.com.banco.models.ContaModel;
import br.com.banco.models.TransferenciaModel;
import br.com.banco.utils.TesteUtils;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {
	
	@Mock
	private ContaDAO contaDAO;
	
	@InjectMocks
	private ContaService contaService;
	
	private ModelMapper mapper = new ModelMapper();
	
	ContaModel conta = TesteUtils.buildConta();
	TransferenciaModel transf = TesteUtils.buildTransferencia();
	
	
	
	// ==================== [ FIND ALL - TEST ] ==================== 
	
	@Test
	void whenTheMethodFindAllIsCalled_ThenAListOfContaIsReturned() {
		
		when(this.contaDAO.findAll(Mockito.any(PageRequest.class)))
			.thenReturn(new PageImpl<>(List.of(this.conta)));
		
		Pageable pageable = PageRequest.of(0, 3, Sort.by("name"));
		
		Page<ContaModel> listOfConta = this.contaService.findAll(pageable);
		
		
		assertEquals(this.conta.getIdConta(), listOfConta.getContent().get(0).getIdConta());
		
		assertEquals(1, listOfConta.getTotalPages());
		
	}
	
	
	
	
	// ==================== [ FIND BY ID ] ====================
	
	@Test
	void whenTheMethodFindByContaNumeroIsCalled_ThenAContaIsReturned() throws BancoNotFoundException {
		
		
		when(this.contaDAO.findById(Mockito.anyLong()))
			.thenReturn(Optional.of(this.conta));
		
		ContaModel foundConta = this.contaService.findByContaNumero(this.conta.getIdConta());
		
		assertEquals(this.conta.getIdConta(), foundConta.getIdConta());
		assertEquals(this.conta.getNomeResponsavel(), foundConta.getNomeResponsavel());
		
		
	}
	
	
	@Test
	void whenTheMethodFindByContaNumeroIsCalledWithInvalidId_ThenAnExceptionIsThrown() throws BancoNotFoundException {
		
		
		when(this.contaDAO.findById(Mockito.anyLong()))
			.thenReturn(Optional.empty());
		
		assertThrows(BancoNotFoundException.class,
				() -> this.contaService.findByContaNumero(this.conta.getIdConta()));
		
		
	}
	
	
	
	
	// ==================== [ TRANSACOES ] ====================
	
	@Test
	void whenTheMethodTransacaoIsCalled_ThenATrabsacaoIsCreated() throws BancoNotFoundException {
		
		when(this.contaDAO.findById(Mockito.anyLong()))
			.thenReturn(Optional.of(this.conta));
		
		when(this.contaDAO.save(Mockito.any(ContaModel.class)))
			.thenReturn(this.conta);
		
		this.contaService.transacao(this.conta.getIdConta(), this.mapper.map(this.transf, TransferenciaDTO.class));
		
		verify(this.contaDAO, times(1)).findById(1L);
		verify(this.contaDAO, times(1)).save(this.conta);
		
		
	}
	

}
