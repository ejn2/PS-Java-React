package br.com.banco.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.banco.models.TransferenciaModel;

public interface TransferenciaDAO extends PagingAndSortingRepository<TransferenciaModel, Long>{

	Optional<TransferenciaModel> findByNomeOperadorTransacao(String operadorName);
	
	List<TransferenciaModel> findByDataTransferenciaBetween(LocalDateTime startDate, LocalDateTime endDate);
	
	List<TransferenciaModel> findByDataTransferenciaBetweenAndNomeOperadorTransacao(LocalDateTime startDate, LocalDateTime endDate, String nomeOperador);
	
}
