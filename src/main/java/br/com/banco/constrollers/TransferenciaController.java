package br.com.banco.constrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.exceptions.BancoNotFoundException;
import br.com.banco.models.TransferenciaModel;
import br.com.banco.services.TransferenciaService;

@CrossOrigin
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
	
	
	// ==================== [ FIND ALL WITH FILTER ] ==================== 
	
	@GetMapping(path = "/filter")
	public Page<TransferenciaModel> findAll(Pageable pageable, @RequestParam(name = "start_date", required=false, defaultValue = "") String startDate, @RequestParam(name = "end_date", required=false, defaultValue = "") String endDate, @RequestParam(name = "nome_operador", required=false, defaultValue = "") String nomeOperador) {
		return this.transferenciaService.findAllFilter(pageable, startDate, endDate, nomeOperador);
	}
	
	
	// ==================== [ FIND BY NAME ] ==================== 
	
	@GetMapping(path = "/{operadorName}")
	public TransferenciaModel findByName(@PathVariable String operadorName) throws BancoNotFoundException {
		return this.transferenciaService.findByName(operadorName);
		
	}
	
}
