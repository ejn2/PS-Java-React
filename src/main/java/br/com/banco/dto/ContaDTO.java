package br.com.banco.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.banco.models.TransferenciaModel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContaDTO {

	private Long idConta;
	
	private String nomeResponsavel;
	
	private List<TransferenciaModel> transferencia = new ArrayList<>();

}
