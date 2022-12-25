package br.com.banco.constrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.dto.ContaDTO;
import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.exceptions.BancoNotFoundException;
import br.com.banco.models.ContaModel;
import br.com.banco.services.ContaService;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/conta")
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	
	// ==================== [ FIND ALL ] ==================== 
	
	@GetMapping
	public Page<ContaModel> findAll(Pageable pageable) {
		return this.contaService.findAll(pageable);
	}
	
	
	
	// ==================== [ FIND BY ID ] ==================== 
	
	@GetMapping(path = "/{id}")
	public ContaModel findByContaNumero(@PathVariable Long id) throws BancoNotFoundException {
		return this.contaService.findByContaNumero(id);
	}
	
	
	// ==================== [ SAVE CONTA ] ==================== 
	
	@PostMapping
	public ContaDTO saveConta(@RequestBody ContaDTO contaDTO) {
		return this.contaService.saveConta(contaDTO);
	}
	
	
	// ==================== [ Transacoes ] ==================== 
	
	@PatchMapping(path="/transacao/{numeroConta}")
	public void transacoe(@PathVariable Long numeroConta, @RequestBody TransferenciaDTO transferenciaDTO) throws BancoNotFoundException {
		this.contaService.transacao(numeroConta, transferenciaDTO);
		
	}
	
	
}
