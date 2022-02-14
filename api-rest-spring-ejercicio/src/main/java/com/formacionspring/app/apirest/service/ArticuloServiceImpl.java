package com.formacionspring.app.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticuloServiceImpl implements ArticuloService 
{
//	@Autowired
//	private ArticuloDao clienteDao;
//	
//	@Override
//	@Transactional(readOnly = true)
//	public List <Articulo> findAll() 
//	{
//		return (List<Articulo>) ArticuloDao.findAll();
//	}
//	
//	@Override
//	@Transactional(readOnly = true)
//	public Articulo findById(Long id) 
//	{
//		return ArticuloDao.findById(id).orElse(null);
//	}
//	
//	@Override
//	@Transactional
//	public Articulo save(Articulo articulo)
//	{
//		return ArticuloDao.save(articulo);
//	}
//
//	@Override
//	@Transactional
//	public void delete(Long id) 
//	{
//		ArticuloDao.deleteById(id);
//	}
}
