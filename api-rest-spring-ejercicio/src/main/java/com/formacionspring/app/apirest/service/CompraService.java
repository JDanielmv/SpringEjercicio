package com.formacionspring.app.apirest.service;

import java.util.List;


import com.formacionspring.app.apirest.entity.Compra;



public interface CompraService 
{
	public List <Compra> findAll(); 
	public Compra findById(Long id);
	public Compra save(Compra compra);
	public void delete (Long id);
}
