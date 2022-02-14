package com.formacionspring.app.apirest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name="articulos")
public class Articulo implements Serializable{

		@Id
		@GeneratedValue(strategy= GenerationType.IDENTITY)
		private long codArticulo; 
		
		@Column(nullable=false)//con esto conseguimos que los atributos no puedan ser nulos
		private String nombre;
		
		private String descripcion;
		@Column(nullable=false)
		private float precio;
		
		private int unidad;
		private int stockseg;
		private String imagen;
		
		
		
		
		public long getCodArticulo() {
			return codArticulo;
		}




		public void setCodArticulo(long codArticulo) {
			this.codArticulo = codArticulo;
		}




		public String getNombre() {
			return nombre;
		}




		public void setNombre(String nombre) {
			this.nombre = nombre;
		}




		public String getDescripcion() {
			return descripcion;
		}




		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}




		public float getPrecio() {
			return precio;
		}




		public void setPrecio(float precio) {
			this.precio = precio;
		}




		public int getUnidad() {
			return unidad;
		}




		public void setUnidad(int unidad) {
			this.unidad = unidad;
		}




		public int getStockseg() {
			return stockseg;
		}




		public void setStockseg(int stockseg) {
			this.stockseg = stockseg;
		}




		public String getImagen() {
			return imagen;
		}




		public void setImagen(String imagen) {
			this.imagen = imagen;
		}




		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
