package com.formacionspring.app.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table( name="compras")
public class Compra implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long codCompra;
	
	@Column(nullable=false)
	private Date fecha;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_cliente")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Cliente codCliente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_articulo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})


	private Articulo codArticulo;
	


	private int unidades;
	
	
	
	
	public long getCodCompra() {
		return codCompra;
	}




	public void setCodCompra(long codCompra) {
		this.codCompra = codCompra;
	}




	public Cliente getCodCliente() {
		return codCliente;
	}




	public Articulo getCodArticulo() {
		return codArticulo;
	}




	public Date getFecha() {
		return fecha;
	}




	public int getUnidades() {
		return unidades;
	}




	public void setCodCliente(Cliente codCliente) {
		this.codCliente = codCliente;
	}




	public void setCodArticulo(Articulo codArticulo) {
		this.codArticulo = codArticulo;
	}




	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}




	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
