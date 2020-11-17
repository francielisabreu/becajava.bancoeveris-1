package br.bancoeveris.app.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.bancoeveris.app.model.Conta;
import br.bancoeveris.app.repository.ContaRepository;
import br.bancoeveris.app.request.ContaRequest;
import br.bancoeveris.app.response.BaseResponse;
import br.bancoeveris.app.response.ContaResponse;

@Service
public class ContaService {
	@Autowired
	private ContaRepository _repository;
	@Autowired
	private OperacaoService _operacaoService;
	

	public ContaResponse saldo(String hash, ContaResponse response) {
		Conta conta = new Conta();
		
		
		conta = _repository.findByHash(hash);

		if (conta == null) {
			response.setMessage("Conta não encontrada!");	
			response.setStatusCode(400);
		}
		
		double saldo =  _operacaoService.saldo(conta.getId());
		conta.setSaldo(saldo);
		
		conta = new Conta();
		conta.setHash(hash);
		conta.setNumConta(conta.getNumConta());
		conta.setAgencia(conta.getAgencia());
	
		return response;
	}

	public ContaResponse inserir(ContaRequest contaRequest) {
		ContaResponse response = new ContaResponse();
		Conta conta = null;	
		
		conta = _repository.findByHash(contaRequest.getHash());
		
		if (conta != null) {
			response.setAgencia(conta.getAgencia());
			response.setHash(conta.getHash());
			response.setNumConta(conta.getNumConta());
			response.setStatusCode(200);
			response.setMessage("Conta já existe.");
			return response;
		}
		if (contaRequest.getHash() == "") {			
			return  new ContaResponse(400,"O Hash do cliente não foi preenchido.");
		}
		if (contaRequest.getNumConta() == "") {			
			return  new ContaResponse(400,"O Número da conta do cliente não foi preenchido.");
		}
		if (contaRequest.getAgencia() == "") {
			return  new ContaResponse(400,"A agencia do cliente não foi preenchido.");
		}
		
		conta = new Conta();
		String hash = UUID.randomUUID().toString().substring(0, 16).replace("-", "");
		conta.setHash(hash);
		conta.setNumConta(contaRequest.getNumConta());
		conta.setAgencia(contaRequest.getAgencia());
		
		conta = _repository.save(conta);
		
		response.setAgencia(conta.getAgencia());
		response.setHash(conta.getHash());
		response.setNumConta(conta.getNumConta());
		response.setStatusCode(201);
		response.setMessage("Conta criada com sucesso.");
		return  response;
	}

	public ContaResponse obter(Long id) {
		Optional<Conta> conta = _repository.findById(id);
		ContaResponse response = new ContaResponse();

		if (conta == null) {
			return  new ContaResponse(400,"Conta não encontrada!");
		}
		
		response.setAgencia(conta.get().getAgencia());
		response.setNumConta(conta.get().getNumConta());
		response.setHash(conta.get().getHash());
		response.setMessage("Conta obtida com sucesso");
		response.setStatusCode(200);
		return response;
	}


	public BaseResponse atualizar(Long id, ContaRequest contaRequest) {
		Conta conta = new Conta();
		BaseResponse base = new BaseResponse();
		

		if (contaRequest.getHash() == "") 
			return  new BaseResponse(400,"O Hash da conta não foi preenchido.");
		
		if (contaRequest.getNumConta() == "")
			return  new BaseResponse(400,"O número da conta não foi preenchido");
		
		if (contaRequest.getAgencia() == "") 			
			return  new BaseResponse(400,"A agencia da conta não foi preenchida");
		

		conta.setId(id);
		conta.setHash(contaRequest.getHash());
		conta.setAgencia(contaRequest.getAgencia());
		conta.setNumConta(contaRequest.getNumConta());
		
		_repository.save(conta);		
		return  new BaseResponse(200,"Conta atualizada com sucesso.");
	}

}