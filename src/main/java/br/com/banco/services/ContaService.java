package br.com.banco.services;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.banco.dao.ContaDAO;
import br.com.banco.dao.TransferenciaDAO;
import br.com.banco.dto.ContaDTO;
import br.com.banco.dto.TransferenciaDTO;
import br.com.banco.exceptions.BancoNotFoundException;
import br.com.banco.models.ContaModel;
import br.com.banco.models.TransferenciaModel;

@Service
public class ContaService {

	@Autowired
	private ContaDAO contaDAO;
	
	@Autowired
	private TransferenciaDAO transferenciaDAO;
	
	private ModelMapper mapper = new ModelMapper();
	
	
	// ==================== [ FIND ALL ] ==================== 
	
	public Page<ContaModel> findAll(Pageable pageable) {
		return this.contaDAO.findAll(pageable);
	}
	
	
	// ==================== [ FIND BY ID ] ==================== 
	
	public ContaModel findByContaNumero(Long id) throws BancoNotFoundException {
		
		return this.contaDAO.findById(id)
			.orElseThrow(() -> new BancoNotFoundException("Numero da conta inexistente."));
		
	}
	
	
	
	// ==================== [ SAVE CONTA ] ==================== 
	
	public ContaDTO saveConta(ContaDTO contaDTO) {
		 ContaModel savedConta = this.contaDAO.save(this.convertToModel(contaDTO));
		 
		 return this.convertToDto(savedConta);
		
	}
	
	
	
	// ==================== [ Transferencia ] ====================
	
	public void transacao(Long numeroConta, TransferenciaDTO transferenciaDTO) throws BancoNotFoundException {
		
		ContaModel contaModel = this.contaDAO.findById(numeroConta)
					.orElseThrow(() -> new BancoNotFoundException("Conta inexistente."));
		
		transferenciaDTO.setContaId(numeroConta);
		transferenciaDTO.setDataTransferencia(LocalDateTime.now());
		
		contaModel.getTransferencia().add(
				this.mapper.map(transferenciaDTO,
				TransferenciaModel.class)
			);
		
		this.contaDAO.save(contaModel);
		
	}
	
	
	private ContaDTO convertToDto(ContaModel contaModel) {
		return this.mapper.map(contaModel, ContaDTO.class);
	}
	
	private ContaModel convertToModel(ContaDTO contaDTO) {
		return this.mapper.map(contaDTO, ContaModel.class);
	}
	
	
	
	
}
