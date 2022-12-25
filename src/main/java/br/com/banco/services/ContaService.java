package br.com.banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.banco.dao.ContaDAO;
import br.com.banco.models.ContaModel;

@Service
public class ContaService {

	@Autowired
	private ContaDAO contaDAO;
	
	
	// ==================== [ FID ALL ] ==================== 
	
	public Page<ContaModel> findAll(Pageable pageable) {
		return this.contaDAO.findAll(pageable);
	}
	
}
