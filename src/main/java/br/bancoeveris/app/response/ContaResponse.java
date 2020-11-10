package br.bancoeveris.app.response;

import br.bancoeveris.app.model.Cliente;

public class ContaResponse extends BaseResponse {

	
	private Long id;	
	private double saldo;
	private String numConta;
	private String agencia;
	private String hash;
	private ClienteResponse clienteResponse;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getNumConta() {
		return numConta;
	}
	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}

	public ClienteResponse getClienteResponse() {
		return clienteResponse;
	}
	public void setClienteResponse(ClienteResponse clienteResponse) {
		this.clienteResponse = clienteResponse;
	}
	
}
