package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credito_cliente")
public class CreditoCliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long folio;
	
	@Column(name = "rut")
	private String rut;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "monto_credito")
	private int montoCredito;
	
	@Column(name = "cuotas")
	private int cuotas;
	
	@Column(name = "tasa_interes")
	private double tasaInteres;
	
	@Column(name = "valor_cuota")
	private int valorCuota;
	
	public CreditoCliente() {
		super();
	}
	
	public CreditoCliente(String rut, Date fecha, int montoCredito, int cuotas, int tasaInteres, int valorCuota) {
		super();
		this.rut = rut;
		this.fecha = fecha;
		this.montoCredito = montoCredito;
		this.cuotas = cuotas;
		this.tasaInteres = tasaInteres;
		this.valorCuota = valorCuota;
	}
	public long getFolio() {
		return folio;
	}
	public void setFolio(long folio) {
		this.folio = folio;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getMontoCredito() {
		return montoCredito;
	}
	public void setMontoCredito(int montoCredito) {
		this.montoCredito = montoCredito;
	}
	public int getCuotas() {
		return cuotas;
	}
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}
	public double getTasaInteres() {
		return tasaInteres;
	}
	public void setTasaInteres(double tasaInteres) {
		this.tasaInteres = tasaInteres;
	}
	public int getValorCuota() {
		return valorCuota;
	}
	public void setValorCuota(int valorCuota) {
		this.valorCuota = valorCuota;
	}
}
