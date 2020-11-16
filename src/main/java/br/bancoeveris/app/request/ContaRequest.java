package br.bancoeveris.app.request;

public class ContaRequest {

	private String hash;
	private String numConta;
	private String agencia;
	
	public String getHash() {
        return this.hash;
    }
	public void setHash(String hash) {
		this.hash = hash;
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
		
}
