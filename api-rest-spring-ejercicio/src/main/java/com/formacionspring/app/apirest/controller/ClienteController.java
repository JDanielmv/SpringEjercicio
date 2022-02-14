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

import com.formacionspring.app.apirest.entity.Cliente;
import com.formacionspring.app.apirest.service.ArticuloService;
import com.formacionspring.app.apirest.service.ClienteService;

@RestController
@RequestMapping("/app")
public class ClienteController {
	
	@Autowired
	private ClienteService servicio;
	

	
	@GetMapping("/clientes")
	public List<Cliente> clientes(){
		return servicio.findAll();
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> mostrarClientes(@PathVariable Long id){
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
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> updateCliente( @RequestBody Cliente cliente, @PathVariable long id) {
		Cliente clienteActual=servicio.findById(id);
		Map<String,Object> response=new HashMap<>();
		try {
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmpresa(cliente.getEmpresa());
			clienteActual.setPuesto(cliente.getPuesto());
			clienteActual.setCp(cliente.getCp());
			clienteActual.setProvincia(cliente.getProvincia());
			clienteActual.setTelefono(cliente.getTelefono());
			clienteActual.setFechaNacimiento(cliente.getFechaNacimiento());
			servicio.save(clienteActual);
		
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar modificacion en el cliente ");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido modificado  con exito");
		response.put("cliente", clienteActual);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> deleteCleinte(@PathVariable Long id) {
		Cliente clienteBorrado=null;
		
		Map<String,Object> response=new HashMap<>();
				
			clienteBorrado=servicio.findById(id);
			if(clienteBorrado==null) {
				response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

			}else {
				try {
					servicio.delete(id);
				} catch (DataAccessException e) {
					response.put("mensaje", "Error al borrar el cliente ");
					response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

		response.put("mensaje", "El cliente ha sido borrado con exito");
		response.put("cliente", clienteBorrado);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}

}
