package com.formacionspring.app.apirest.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.app.apirest.entity.Articulo;
import com.formacionspring.app.apirest.entity.articulo;
import com.formacionspring.app.apirest.entity.articulo;
import com.formacionspring.app.apirest.service.ArticuloService;

@RestController
@RequestMapping("/app")
public class ArticuloController {
	@Autowired
	private ArticuloService servicio;
	
	@GetMapping("/articulos")
	public List<Articulo> articulos(){
		return servicio.findAll();
	}
	
	@GetMapping("/articulos/{id}")
	public ResponseEntity<?> mostrarArticulos(@PathVariable Long id){
		Articulo articulo=null;
		Map<String,Object> response=new HashMap<>();
		
		try {
			articulo=servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(articulo==null) {
			response.put("mensaje", "El Arrticulo con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

		}
		
		return new ResponseEntity<Articulo>(articulo,HttpStatus.OK);
	}
	
	@PutMapping("/articulos/{id}")
	public ResponseEntity<?> updatearticulo( @RequestBody Articulo articulo, @PathVariable long id) {
		Articulo articuloActual=servicio.findById(id);
		Map<String,Object> response=new HashMap<>();
		try {
			articuloActual.setNombre(articulo.getNombre());
			articuloActual.setDescripcion(articulo.getDescripcion());
			articuloActual.setPrecio(articulo.getPrecio());
			articuloActual.setUnidad(articulo.getUnidad());
			articuloActual.setStockseg(articulo.getStockseg());
			articuloActual.setImagen(articulo.getImagen());
			servicio.save(articuloActual);
		
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar modificacion en el articulo ");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El articulo ha sido modificado  con exito");
		response.put("articulo", articuloActual);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	

	@DeleteMapping("/articulos/{id}")
	public ResponseEntity<?> deleteCleinte(@PathVariable Long id) {
		Articulo articuloBorrado=null;
		
		Map<String,Object> response=new HashMap<>();
				
			articuloBorrado=servicio.findById(id);
			if(articuloBorrado==null) {
				response.put("mensaje", "El articulo ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

			}else {
				try {
					servicio.delete(id);
				} catch (DataAccessException e) {
					response.put("mensaje", "Error al borrar el articulo ");
					response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

		response.put("mensaje", "El articulo ha sido borrado con exito");
		response.put("articulo", articuloBorrado);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}

}
