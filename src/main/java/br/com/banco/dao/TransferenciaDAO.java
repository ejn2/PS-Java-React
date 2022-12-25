package br.com.banco.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.banco.models.TransferenciaModel;

public interface TransferenciaDAO extends PagingAndSortingRepository<TransferenciaModel, Long>{

}
