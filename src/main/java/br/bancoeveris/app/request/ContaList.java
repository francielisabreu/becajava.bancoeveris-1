package br.bancoeveris.app.request;

import java.util.List;

import br.bancoeveris.app.model.Conta;
import br.bancoeveris.app.response.BaseResponse;

public class ContaList extends BaseResponse {
	
	private List<Conta> Contas;

	public List<Conta> getContas() {
		return Contas;
	}

	public void setContas(List<Conta> contas) {
		Contas = contas;
	}

}