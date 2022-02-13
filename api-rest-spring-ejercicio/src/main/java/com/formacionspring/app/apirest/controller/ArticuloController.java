package com.formacionspring.app.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.app.apirest.entity.Cliente;
import com.formacionspring.app.apirest.service.ArticuloService;

@RestController
@RequestMapping("/app")
public class ArticuloController {
	@Autowired
	private ArticuloService servicio;
	
	@GetMapping("/articulos")
	public List<Cliente> clientes(){
		return servicio.findAll();
	}
	
	@GetMapping("/articulos/{id}")
	public ResponseEntity<?> mostrarArticulos(@PathVariable Long id){
		Cliente cliente=null;
		Map<String,Object> response=new HashMap<>();
		
		try {
			cliente=servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliente==null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

		}
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
}
