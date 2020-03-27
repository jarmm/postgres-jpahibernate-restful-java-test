package com.example.demo.dto;

public class ValorCuotaDto {
	
	private double tasaInteres;
	private int valorCuota;
	
	public ValorCuotaDto() {
		super();
	}
	public ValorCuotaDto(double tasaInteres, int valorCuota) {
		super();
		this.tasaInteres = tasaInteres;
		this.valorCuota = valorCuota;
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
