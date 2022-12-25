package br.com.banco.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "conta")
public class ContaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conta")
	private Long idConta;
	
	@Column(name = "nome_responsavel", length = 50,nullable = false)
	private String nomeResponsavel;
	

	@OneToMany(cascade = CascadeType.MERGE)
	@JoinColumn(name = "conta_id")
	private List<TransferenciaModel> transferencia = new ArrayList<>();

}
