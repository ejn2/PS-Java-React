package br.com.banco.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "transferencia")
public class TransferenciaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data_transferencia", nullable = false)
	private LocalDateTime dataTransferencia;

	@Column(name = "valor", precision = 20, scale = 2, nullable = false)
	private Double valor;
	
	@Column(name = "tipo", length = 15, nullable = false)
	private String tipo;
	
	@Column(name = "nome_operador_transacao", length = 50, nullable = false)
	private String nomeOperadorTransacao;
	
	@Column(name = "conta_id",nullable = false)
	private Long contaId;
	
}
