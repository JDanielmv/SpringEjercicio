package com.formacionspring.app.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.formacionspring.app.apirest.entity.Articulo;
@Repository
public interface ArticuloDao extends CrudRepository<Articulo, Long>{

	
	
}
