package br.bancoeveris.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.bancoeveris.app.model.Operacao;
import br.bancoeveris.app.request.OperacaoList;
import br.bancoeveris.app.request.OperacaoRequest;
import br.bancoeveris.app.request.TransferenciaRequest;
import br.bancoeveris.app.response.BaseResponse;
import br.bancoeveris.app.service.OperacaoService;

@RestController
@RequestMapping("/operacoes")
public class OperacaoController extends BaseController {
	
	private final OperacaoService _service;
	
	@Autowired
	public OperacaoController(OperacaoService service) {
		_service = service;
	}
	
	@PostMapping (path = "/depositoSaque")
    public ResponseEntity inserir(@RequestBody OperacaoRequest operacaoSpec) {
		try {
			BaseResponse response = _service.inserir(operacaoSpec);
			return ResponseEntity.status(response.getStatusCode()).body(response);			
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.getStatusCode()).body(errorBase);
		}
    }
	@PostMapping (path = "/tranferencia")
    public ResponseEntity transferencia(@RequestBody TransferenciaRequest transferenciaSpec) {
		try {
			BaseResponse response = _service.transferencia(transferenciaSpec);
			return ResponseEntity.status(response.getStatusCode()).body(response);			
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.getStatusCode()).body(errorBase);
		}
    }
	
}