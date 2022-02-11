package com.formacionspring.app.apirest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long codCliente;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private String apellido;
	
	private String empresa;
	private String puesto;
	private int cp;
	private String provincia;
	private int telefono;
	
}
