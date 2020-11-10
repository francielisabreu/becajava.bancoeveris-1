package br.bancoeveris.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.bancoeveris.app.model.*;
import br.bancoeveris.app.request.ClienteList;
import br.bancoeveris.app.request.ClienteRequest;
import br.bancoeveris.app.response.BaseResponse;
import br.bancoeveris.app.response.ClienteResponse;
import br.bancoeveris.app.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController extends BaseController {
	
	private final ClienteService _service;
	
	@Autowired
	public ClienteController(ClienteService service) {
		_service = service;
	}
	
	@PostMapping
    public ResponseEntity inserir(@RequestBody ClienteRequest clienteRequest) {
		try {
			BaseResponse response = _service.inserir(clienteRequest);
			return ResponseEntity.status(response.StatusCode).body(response);			
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
		}
    }
	
	@GetMapping(path = "/{id}")
    public ResponseEntity obter(@PathVariable Long id) {		
		try {
			ClienteResponse response = _service.obter(id);
			return ResponseEntity.status(response.StatusCode).body(response);	
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
		}   	
    }

	@GetMapping
    public ResponseEntity listar() {		
		try {
			ClienteList clientes = _service.listar();  		
	    	return ResponseEntity.status(HttpStatus.OK).body(clientes);			
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);			
		}		
    }
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity deletar(@PathVariable Long id) {
		try {
			BaseResponse response = _service.deletar(id);
			return ResponseEntity.status(response.StatusCode).build(); 
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
		}
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity atualizar(@RequestBody ClienteRequest clienteRequest, @PathVariable Long id) {
		try {
			BaseResponse response = _service.atualizar(id, clienteRequest);
			return ResponseEntity.status(response.StatusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
		}
	}

}