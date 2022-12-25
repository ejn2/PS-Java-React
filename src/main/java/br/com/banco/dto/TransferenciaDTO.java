package br.com.banco.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class TransferenciaDTO {

	private Long id;

	private LocalDateTime dataTransferencia;

	private Double valor;

	private String tipo;
	
	private String nomeOperadorTransacao;

	private Long contaId;
}
