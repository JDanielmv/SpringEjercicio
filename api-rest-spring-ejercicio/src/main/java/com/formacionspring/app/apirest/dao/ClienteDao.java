package com.formacionspring.app.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.app.apirest.entity.Cliente;
import com.formacionspring.app.apirest.entity.Compra;

@Repository
public interface ClienteDao extends CrudRepository<Cliente, Long>{

	@Query("from Compra")
	public List<Compra> findAllCompras();
	
}
