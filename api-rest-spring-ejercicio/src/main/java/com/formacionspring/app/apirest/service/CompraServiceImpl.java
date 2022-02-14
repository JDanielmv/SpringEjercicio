package com.formacionspring.app.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.formacionspring.app.apirest.dao.CompraDao;

import com.formacionspring.app.apirest.entity.Compra;


@Service
public class CompraServiceImpl implements CompraService
{
	@Autowired
	private CompraDao compraDao;

	@Override
	public List<Compra> findAll() {
		// TODO Auto-generated method stub
		return (List<Compra>) compraDao.findAll();
	}

	@Override
	public Compra findById(Long id) {
		// TODO Auto-generated method stub
		return compraDao.findById(id).orElse(null);
	}

	@Override
	public Compra save(Compra compra) {
		// TODO Auto-generated method stub
		return compraDao.save(compra);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		compraDao.deleteById(id);
	}

	


	

}
