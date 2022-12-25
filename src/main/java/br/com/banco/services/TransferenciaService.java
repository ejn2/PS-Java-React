package br.com.banco.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.banco.dao.TransferenciaDAO;
import br.com.banco.exceptions.BancoNotFoundException;
import br.com.banco.models.TransferenciaModel;

@Service
public class TransferenciaService {

	
	@Autowired
	private TransferenciaDAO transferenciaDAO;
	
	
	
	// ==================== [ FIND ALL ] ==================== 
	
	public Page<TransferenciaModel> findAll(Pageable pageable) {
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
