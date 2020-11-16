package br.bancoeveris.app.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "hash", name = "hash_uk"))//Constraint para que o HASH seja unico
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Transient
	private double saldo;
	private String numConta;
	private String agencia;
	private String hash;
		
	public Conta() {
		
	}
	
	
	public Conta(Long id) {
		super();
		this.id = id;
	}
	public Conta(double saldo, String numConta, String agencia, String hash) {
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
