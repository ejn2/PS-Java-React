package br.com.banco.constrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.models.TransferenciaModel;
import br.com.banco.services.TransferenciaService;

@RestController
@RequestMapping(path = "/api/v1/transferencia")
public class TransferenciaController {

	@Autowired
	private TransferenciaService transferenciaService;
	
	
	// ==================== [ FIND ALL ] ==================== 
	
	@GetMapping
	public Page<TransferenciaModel> findAll(Pageable pageable) {
		return this.transferenciaService.findAll(pageable);
	}
	
}
