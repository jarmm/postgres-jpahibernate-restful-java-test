package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ValorCuotaDto;
import com.example.demo.repository.CreditoClienteRepository;

@Service
public class CreditoClienteService {
	
	@Autowired
    private CreditoClienteRepository clienteRepository;
	
	public boolean existsById(Long folio) {
        return clienteRepository.existsById(folio);
    }
	
	public boolean validarMonto(int monto) {
		if(monto<=0) {
			return false;
		}
		return true;
	}
	
	public boolean validarRangoCuotas(int cuotas) {
		if(cuotas<1 || cuotas>60) {
			return false;
		}
		return true;
	}
	
	public ValorCuotaDto calcularValorCuota(int numCuotas, int montoCredito) {
		int valorCuota = 0;	
		double tasaInteres=0;
		double interesPorCuota = 0;
		
		if(numCuotas==1){
			tasaInteres=0;
		}
		if((numCuotas <= 3) && (numCuotas > 1)){
			tasaInteres = 0.5;
		}
		if((numCuotas <= 12) && (numCuotas >= 6)){
			tasaInteres = 1;
		}
		if((numCuotas <= 24) && (numCuotas >= 13)){
			tasaInteres = 1.5;
		}
		if((numCuotas <= 36) && (numCuotas >= 25)){
			tasaInteres = 2;
		}
		if((numCuotas <= 48) && (numCuotas >= 37)){
			tasaInteres = 2.5;
		}
		if((numCuotas <= 60) && (numCuotas >= 49)){
			tasaInteres = 3;
		}
		
		interesPorCuota = montoCredito*tasaInteres/100;
		valorCuota=(int) Math.round((montoCredito/numCuotas)+interesPorCuota);
		return new ValorCuotaDto(tasaInteres, valorCuota);
	}
	
}
