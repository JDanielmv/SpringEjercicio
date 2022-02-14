package com.formacionspring.app.apirest.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.formacionspring.app.apirest.entity.Articulo;
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
		
		response.put("articulo", articuloActual);
		response.put("mensaje", "El articulo ha sido modificado  con exito");
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
					String nombreFotoAnterior = articuloBorrado.getImagen();
					
					if(nombreFotoAnterior !=null && nombreFotoAnterior.length()>0 ) {
						
						Path rutaFotoAnterior= Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
						File archivoFotoanterior = rutaFotoAnterior.toFile();
						
						if(archivoFotoanterior.exists() && archivoFotoanterior.canRead() ) {
							
							archivoFotoanterior.delete();
						}
					}
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
	
	@PostMapping("/articulos/upload")
	public ResponseEntity<?> uploadImagen(@RequestParam("archivo")MultipartFile archivo, @RequestParam("id")Long id){
		Map<String,Object> response=new HashMap<>();
		Articulo articulo=servicio.findById(id);
		if(!archivo.isEmpty()) {
			String nombreArchivo= UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ","");
			Path rutaArchivo=Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al intentar subir la imagen ");
				response.put("error", e.getMessage().concat("_ ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = articulo.getImagen();
			
			if(nombreFotoAnterior !=null && nombreFotoAnterior.length()>0 ) {
				
				Path rutaFotoAnterior= Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoanterior = rutaFotoAnterior.toFile();
				
				if(archivoFotoanterior.exists() && archivoFotoanterior.canRead() ) {
					
					archivoFotoanterior.delete();
				
				}
			}

			articulo.setImagen(nombreArchivo);
			servicio.save(articulo);
			response.put("mensaje", "Se ha subido correctamente el archivo: " + nombreArchivo);
			response.put("articulo", articulo);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}else {
			response.put("Archivo", "Archivo vacio");
		}
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/articulos/uploads/img/{nombreImagen:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String nombreImagen){
		Path rutaArchivo=Paths.get("uploads").resolve(nombreImagen).toAbsolutePath();
		Resource recurso=null;
		try {
			recurso= new UrlResource(rutaArchivo.toUri());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists()&&!recurso.isReadable()) {
			throw new RuntimeException("Error no se puede cargar la imagen " +nombreImagen);
		}
		
		HttpHeaders cabecera=new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""+recurso.getFilename()+"\"");
		return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
	}
	
	@PostMapping("/articulos")
	public ResponseEntity<?> save(@RequestBody Articulo articulo) {
		Articulo articuloNew=null;
		Map<String,Object> response=new HashMap<>();
		try {
			articuloNew=servicio.save(articulo);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insercion en la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El articulo ha sido creado con exito");
		response.put("cliente", articuloNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}

}
