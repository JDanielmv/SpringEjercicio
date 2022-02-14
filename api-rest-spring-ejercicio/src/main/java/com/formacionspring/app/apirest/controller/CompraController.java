package com.formacionspring.app.apirest.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.app.apirest.entity.Compra;
import com.formacionspring.app.apirest.service.CompraService;
@RestController
@RequestMapping("/app")
public class CompraController {
	
	@Autowired
	private CompraService servicio;
	

	
	@GetMapping("/compras")
	public List<Compra> compras(){
		return servicio.findAll();
	}
	
	@GetMapping("/compras/{id}")
	public ResponseEntity<?> mostrarCompras(@PathVariable Long id){
		Compra Compra=null;
		Map<String,Object> response=new HashMap<>();
		
		try {
			Compra=servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(Compra==null) {
			response.put("mensaje", "El Compra ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

		}
		
		return new ResponseEntity<Compra>(Compra,HttpStatus.OK);
	}
	
	@PutMapping("/compras/{id}")
	public ResponseEntity<?> updateCompra( @RequestBody Compra compra, @PathVariable long id) {
		Compra compraActual=servicio.findById(id);
		Map<String,Object> response=new HashMap<>();
		try {
			compraActual.setFecha(compra.getFecha());
			compraActual.setCodCliente(compra.getCodCliente());
			compraActual.setCodArticulo(compra.getCodArticulo());
			compraActual.setUnidades(compra.getUnidades());
			servicio.save(compraActual);
		
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar modificacion en el Compra ");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Compra ha sido modificado  con exito");
		response.put("Compra", compraActual);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/compras/{id}")
	public ResponseEntity<?> deleteCleinte(@PathVariable Long id) {
		Compra compraBorrado=null;
		
		Map<String,Object> response=new HashMap<>();
				
			compraBorrado=servicio.findById(id);
			if(compraBorrado==null) {
				response.put("mensaje", "La Compra con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

			}else {
				try {
					servicio.delete(id);
					
				} catch (DataAccessException e) {
					response.put("mensaje", "Error al borrar la Compra ");
					response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

		response.put("mensaje", "La Compra ha sido borrado con exito");
		response.put("Compra", compraBorrado);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@PostMapping("/compras")
	public ResponseEntity<?> save(@RequestBody Compra Compra) {
		Compra compraNew=null;
		Map<String,Object> response=new HashMap<>();
		try {
			compraNew=servicio.save(Compra);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insercion en la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Compra ha sido creado con exito");
		response.put("Compra", compraNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	


}
