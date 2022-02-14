package com.formacionspring.app.apirest.service;

import java.util.List;

import com.formacionspring.app.apirest.entity.Articulo;

public interface ArticuloService 
{
	public List <Articulo> findAll(); 
	public Articulo findById(Long id);
	public Articulo save(Articulo articulo);
	public void delete (Long id);
}
