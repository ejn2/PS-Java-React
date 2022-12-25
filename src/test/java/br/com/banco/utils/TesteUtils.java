package br.com.banco.utils;

import java.time.LocalDateTime;

import br.com.banco.models.ContaModel;
import br.com.banco.models.TransferenciaModel;

public abstract class TesteUtils {

	
	public static ContaModel buildConta() {
		ContaModel contaModel = new ContaModel();
		contaModel.setIdConta(1L);
		contaModel.setNomeResponsavel("Fulano");
		contaModel.getTransferencia().add(TesteUtils.buildTransferencia());
		
		return contaModel;
		
	}
	
	
	
	public static TransferenciaModel buildTransferencia() {
		TransferenciaModel transferenciaModel = new TransferenciaModel();
		transferenciaModel.setId(1L);
		transferenciaModel.setDataTransferencia(LocalDateTime.of(2019, 1, 1, 7, 0));
		transferenciaModel.setTipo("TRANSFERENCIA");
		transferenciaModel.setValor(3241.23);
		
		return transferenciaModel;
		
	}
	
	
}
