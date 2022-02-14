package com.formacionspring.app.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspring.app.apirest.dao.ArticuloDao;
import com.formacionspring.app.apirest.entity.Articulo;

@Service
public class ArticuloServiceImpl implements ArticuloService 
{
	@Autowired
	private ArticuloDao articuloDao;
	
	@Override
	@Transactional(readOnly = true)
	public List <Articulo> findAll() 
	{
		return (List<Articulo>) articuloDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Articulo findById(Long id) 
	{
		return articuloDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Articulo save(Articulo articulo)
	{
		return articuloDao.save(articulo);
	}

	@Override
	@Transactional
	public void delete(Long id) 
	{
		articuloDao.deleteById(id);
	}
}
