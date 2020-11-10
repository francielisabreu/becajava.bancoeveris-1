package br.bancoeveris.app.request;

import java.util.List;

import br.bancoeveris.app.model.Operacao;
import br.bancoeveris.app.response.BaseResponse;

public class OperacaoList extends BaseResponse {
	
	private List<Operacao> Operacoes;

	public List<Operacao> getOperacoes() {
		return Operacoes;
	}

	public void setOperacoes(List<Operacao> operacoes) {
		Operacoes = operacoes;
	}

}