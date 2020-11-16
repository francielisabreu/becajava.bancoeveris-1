package br.bancoeveris.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.bancoeveris.app.request.ContaRequest;
import br.bancoeveris.app.response.BaseResponse;
import br.bancoeveris.app.response.ContaResponse;
import br.bancoeveris.app.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController extends BaseController {
	@Autowired
	private  ContaService _service;
	
	@PostMapping
    public ResponseEntity inserir(@RequestBody ContaRequest contaRequest) {
		try {
			ContaResponse response = _service.inserir(contaRequest);
			return ResponseEntity.status(response.getStatusCode()).body(response);			
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.getStatusCode()).body(errorBase);
		}
    }
	
	@GetMapping(path = "/{id}")
    public ResponseEntity obter(@PathVariable Long id) {		
		try {
			ContaResponse response = _service.obter(id);
			return ResponseEntity.status(response.getStatusCode()).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.getStatusCode()).body(errorBase);
		}   	
    }
	
	@GetMapping(path = "/saldo/{hash}")
    public ResponseEntity saldo(@PathVariable String hash) {		
		ContaResponse response = new ContaResponse();
		try {
			ContaResponse contaResponse = _service.saldo(hash, response);
			return ResponseEntity.status(response.getStatusCode()).body(response);	
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.getStatusCode()).body(errorBase);
		}   	
    }

	
	@PutMapping(path = "/{id}")
	public ResponseEntity atualizar(@RequestBody ContaRequest contaRequest, @PathVariable Long id) {
		try {
			BaseResponse response = _service.atualizar(id, contaRequest);
			return ResponseEntity.status(response.getStatusCode()).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.getStatusCode()).body(errorBase);
		}
	}
}