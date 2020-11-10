package br.bancoeveris.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.bancoeveris.app.model.Cliente;
import br.bancoeveris.app.model.Conta;
import br.bancoeveris.app.repository.ContaRepository;
import br.bancoeveris.app.request.ContaList;
import br.bancoeveris.app.request.ContaRequest;
import br.bancoeveris.app.response.BaseResponse;
import br.bancoeveris.app.response.ClienteResponse;
import br.bancoeveris.app.response.ContaResponse;

@Service
public class ContaService {

	final ContaRepository _repository;
	final OperacaoService _operacaoService;
	final ClienteService _clienteService;

	public ContaService(ContaRepository repository, OperacaoService operacaoService, ClienteService clienteService) {
		_repository = repository;
		_operacaoService = operacaoService;
		_clienteService = clienteService;
	}

	public ContaResponse saldo(String hash) {
		ContaResponse conta = new ContaResponse();
				
		Conta contaCheck = _repository.findByHash(hash);

		if (contaCheck == null) {
			conta.StatusCode = 404;
			conta.Message = "Conta não encontrada!";
			return conta;
		}
		
		double saldo =  _operacaoService.saldo(conta.getId());
		conta.setSaldo(saldo);
		conta.setHash(conta.getHash());
		conta.setAgencia(conta.getAgencia());
		conta.setNumConta(conta.getNumConta());
		conta.setClienteResponse(conta.getClienteResponse());
		
		conta.StatusCode = 200;
		conta.Message = "Consulta de saldo ok!";
		return conta;
	}

	public ContaResponse inserir(ContaRequest contaRequest) {
		ContaResponse response = new ContaResponse();
		Conta conta = new Conta();		
		response.StatusCode = 400;
		
		boolean existe = true; 
		
		while(existe == true) {
			String randomHash = response.getHash();
			Conta contaExiste = _repository.findByHash(randomHash);
			
			if (contaExiste != null)
				existe = true;
			else
				existe = false;
		}
		
		if (contaRequest.getHash() == "") {
			response.Message = "O Hash do cliente não foi preenchido.";
			return response;
		}
		if (contaRequest.getNumConta() == "") {
			response.Message = "O Número da conta do cliente não foi preenchido.";
			return response;
		}
		if (contaRequest.getAgencia() == "") {
			response.Message = "A agencia do cliente não foi preenchido.";
			return response;
		}
		

		response.setHash(contaRequest.getHash());
		response.setNumConta(contaRequest.getNumConta());
		response.setAgencia(contaRequest.getAgencia());
		ClienteResponse cliente = _clienteService.obterByCpf(contaRequest.getCliente().getCpf());

		if (cliente.StatusCode == 404) {
			_clienteService.inserir(contaRequest.getCliente());
			cliente = _clienteService.obterByCpf(contaRequest.getCliente().getCpf());
			
		}
		
//		conta.setCliente(cliente);

		_repository.save(conta);
		response.StatusCode = 201;
		response.Message = "Conta inserida com sucesso.";
		return response;
	}

	public ContaResponse obter(Long id) {
		Optional<Conta> conta = _repository.findById(id);
		ContaResponse response = new ContaResponse();

		if (conta == null) {
			response.Message = "Conta não encontrada";
			response.StatusCode = 404;
			return response;
		}

		response.Message = "Conta obtida com sucesso";
		response.StatusCode = 200;
		response.setAgencia(conta.get().getAgencia());
		response.setNumConta(conta.get().getNumConta());
		response.setHash(conta.get().getHash());
		return response;
	}

//	public ContaList listar() {
//
//		List<Conta> lista = _repository.findAll();
//
//		ContaList response = new ContaList();
//		response.setContas(lista);
//		response.StatusCode = 200;
//		response.Message = "Contas obtidas com sucesso.";
//
//		return response;
//	}

	public BaseResponse atualizar(Long id, ContaRequest contaRequest) {
		Conta conta = new Conta();
		BaseResponse base = new BaseResponse();
		base.StatusCode = 400;

		if (contaRequest.getHash() == "") {
			base.Message = "O Hash da conta não foi preenchido.";
			return base;
		}
		if (contaRequest.getNumConta() == "") {
			base.Message = "O número da conta não foi preenchido";
			return base;
		}
		if (contaRequest.getAgencia() == "") {
			base.Message = "A agencia da conta não foi preenchida";
			return base;
		}

		conta.setId(id);
		conta.setHash(contaRequest.getHash());
		conta.setAgencia(contaRequest.getAgencia());
		conta.setNumConta(contaRequest.getNumConta());
		_clienteService.inserir(contaRequest.getCliente());

		_repository.save(conta);
		base.StatusCode = 200;
		base.Message = "Conta atualizada com sucesso.";
		return base;
	}

//	public BaseResponse deletar(Long id) {
//		BaseResponse response = new BaseResponse();
//
//		if (id == null || id == 0) {
//			response.StatusCode = 400;
//			return response;
//		}
//
//		_repository.deleteById(id);
//		response.StatusCode = 200;
//		return response;
//	}

}