package br.bancoeveris.app.response;



public class ContaResponse extends BaseResponse {

	
	private Long id;	
	private double saldo;
	private String numConta;
	private String agencia;
	private String hash;
;
	
	public ContaResponse(int statusCode, String message) {
		super();
		setStatusCode(statusCode);
		setMessage(message);		
	}
	public ContaResponse() {
		
	}
		
		
	public ContaResponse(Long id) {
		super();
		this.id = id;
	}
	
	public ContaResponse(double saldo, String numConta, String agencia, String hash) {
		super();
		this.saldo = saldo;
		this.numConta = numConta;
		this.agencia = agencia;
		this.hash = hash;	
	}

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

}