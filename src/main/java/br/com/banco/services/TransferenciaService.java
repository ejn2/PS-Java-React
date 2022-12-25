package br.com.banco.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.banco.dao.TransferenciaDAO;
import br.com.banco.exceptions.BancoNotFoundException;
import br.com.banco.models.TransferenciaModel;

@Service
public class TransferenciaService {

	
	@Autowired
	private TransferenciaDAO transferenciaDAO;
	
	
	// ==================== [ String to LocalDateTime ] ==================== 
	
	private LocalDateTime stringToLocalDateTime(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(date.replace('T', ' '), formatter);
		
		return dateTime;
	}
	
	
	// ==================== [ FIND ALL ] ==================== 
	
	
	public Page<TransferenciaModel> findAll(Pageable pageable) {
		return this.transferenciaDAO.findAll(pageable);
	}
	
	
	
	public Page<TransferenciaModel> findAllFilter(Pageable pageable, String startDate, String endDate, String nomeOperador) {
		
		
		if(!startDate.isEmpty() && !endDate.isEmpty() && !nomeOperador.isEmpty()) {
			
			List<TransferenciaModel> allTransferencia = this.transferenciaDAO.findByDataTransferenciaBetweenAndNomeOperadorTransacao(
				this.stringToLocalDateTime(startDate),
				this.stringToLocalDateTime(endDate),
				nomeOperador
					);
			
			return new PageImpl<>(allTransferencia);
		}
		
		
		
		if(!startDate.isEmpty() && !endDate.isEmpty()) {
			
			List<TransferenciaModel> allTransferencia = this.transferenciaDAO.findByDataTransferenciaBetween(
				this.stringToLocalDateTime(startDate),
				this.stringToLocalDateTime(endDate)
					);
			
			return new PageImpl<>(allTransferencia);
		}
		
		
		
		return this.transferenciaDAO.findAll(pageable);
	}
	
	
	// ==================== [ FIND BY NAME ] ==================== 
	
	public TransferenciaModel findByName(String operadorName) throws BancoNotFoundException {
		Optional<TransferenciaModel> transferenciaModel = this.transferenciaDAO.findByNomeOperadorTransacao(operadorName);
		
		if(!transferenciaModel.isPresent()) {
			throw new BancoNotFoundException("Operador não encontrado.");
		}
		
		return transferenciaModel.get();
		
	}
	
}
