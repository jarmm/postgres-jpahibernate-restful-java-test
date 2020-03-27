package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ValorCuotaDto;
import com.example.demo.exception.BadResourceException;
import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CreditoCliente;
import com.example.demo.repository.CreditoClienteRepository;
import com.example.demo.service.CreditoClienteService;
import com.example.demo.utils.Utils;

@RestController
@RequestMapping("/api")
public class CreditoClienteController {
	
	@Autowired
	private CreditoClienteRepository creditoClienteRepository;
	
	@Autowired
    private CreditoClienteService creditoClienteService;

	@GetMapping("/creditos")
	public List<CreditoCliente> getAllCreditos() {
		return creditoClienteRepository.findAll();
	}

	@GetMapping("/credito/{folio}")
	public ResponseEntity<CreditoCliente> getCreditoByFolio(@PathVariable(value = "folio") Long folio)
			throws ResourceNotFoundException {
		CreditoCliente creditoCliente = creditoClienteRepository.findById(folio)
				.orElseThrow(() -> new ResourceNotFoundException("No existe Crédito con folio: " + folio));
		return ResponseEntity.ok().body(creditoCliente);
	}

	@PostMapping("/credito")
	public CreditoCliente createCreditoCliente(@Valid @RequestBody CreditoCliente creditoCliente) throws ResourceAlreadyExistsException, BadResourceException {
		if (creditoClienteService.validarMonto(creditoCliente.getMontoCredito())) {
        	if(Utils.validarRut(creditoCliente.getRut())) {
        		if(creditoClienteService.validarRangoCuotas(creditoCliente.getCuotas())) {
        			ValorCuotaDto valorCuotaDto = creditoClienteService.calcularValorCuota(creditoCliente.getCuotas() ,creditoCliente.getMontoCredito());
            		creditoCliente.setTasaInteres(valorCuotaDto.getTasaInteres());
            		creditoCliente.setValorCuota(valorCuotaDto.getValorCuota());
            		creditoCliente.setFecha(Utils.getFechaActual());
            		return creditoClienteRepository.save(creditoCliente);
        		}else {
        			throw new BadResourceException("Error en guardar, cuotas no pueden ser menor a 1 ni mayor a 60");
        		}
        	}else {
        		throw new BadResourceException("Error en guardar, rut inválido");
        	}
        }else {
        	throw new BadResourceException("El monto no puede ser 0 ni negativo");
        }
	}

	@PutMapping("/credito/{folio}")
	public ResponseEntity<CreditoCliente> updateCreditoCliente(@PathVariable(value = "folio") Long folio, @Valid @RequestBody CreditoCliente creditoClienteOld) throws ResourceNotFoundException, BadResourceException {
		CreditoCliente creditoCliente  = creditoClienteRepository.findById(folio)
				.orElseThrow(() -> new ResourceNotFoundException("No existe Crédito con folio: " + folio));
		if (creditoClienteService.validarMonto(creditoClienteOld.getMontoCredito())) {
        	if(Utils.validarRut(creditoClienteOld.getRut())) {
        		if(creditoClienteService.validarRangoCuotas(creditoCliente.getCuotas())) {
	        		ValorCuotaDto valorCuotaDto = creditoClienteService.calcularValorCuota(creditoClienteOld.getCuotas() ,creditoClienteOld.getMontoCredito());
	        		creditoCliente.setCuotas(creditoClienteOld.getCuotas());
	        		creditoCliente.setTasaInteres(valorCuotaDto.getTasaInteres());
	        		creditoCliente.setValorCuota(valorCuotaDto.getValorCuota());
	        		creditoCliente.setFecha(Utils.getFechaActual());
	        		creditoCliente.setMontoCredito(creditoClienteOld.getMontoCredito());
	        		creditoCliente.setRut(creditoClienteOld.getRut());
	        		final CreditoCliente updatedCreditoCliente = creditoClienteRepository.save(creditoCliente);
	        		return ResponseEntity.ok(updatedCreditoCliente);
        		}else {
        			throw new BadResourceException("Error en guardar, cuotas no pueden ser menor a 1 ni mayor a 60");
        		}	
        	}else {
        		throw new BadResourceException("Error en guardar, rut inválido");
        	}
        }else {
        	throw new BadResourceException("El monto no puede ser 0 ni negativo");
        }
	}

	@DeleteMapping("/credito/{folio}")
	public Map<String, Boolean> deleteCreditoCliente(@PathVariable(value = "folio") Long folio)
			throws ResourceNotFoundException {
		CreditoCliente creditoCliente = creditoClienteRepository.findById(folio)
				.orElseThrow(() -> new ResourceNotFoundException("No existe Crédito con folio: " + folio));

		creditoClienteRepository.delete(creditoCliente);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
