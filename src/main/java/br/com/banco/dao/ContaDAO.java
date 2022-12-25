package br.com.banco.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.banco.models.ContaModel;

public interface ContaDAO extends PagingAndSortingRepository<ContaModel, Long> {
	
}
