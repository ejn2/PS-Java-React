package br.com.banco.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.banco.models.TransferenciaModel;

public interface TransferenciaDAO extends PagingAndSortingRepository<TransferenciaModel, Long>{

	Optional<TransferenciaModel> findByNomeOperadorTransacao(String operadorName);
	
}
