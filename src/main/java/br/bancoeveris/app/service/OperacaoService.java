package br.bancoeveris.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bancoeveris.app.model.Conta;
import br.bancoeveris.app.model.Operacao;
import br.bancoeveris.app.repository.ContaRepository;
import br.bancoeveris.app.repository.OperacaoRepository;

import br.bancoeveris.app.request.OperacaoRequest;
import br.bancoeveris.app.request.TransferenciaRequest;
import br.bancoeveris.app.response.BaseResponse;

@Service
public class OperacaoService {

	@Autowired
	private OperacaoRepository _repository;
	@Autowired
	private ContaRepository _contaRepository;

	public BaseResponse inserir(OperacaoRequest request) {

		Conta conta = _contaRepository.findByHash(request.getHash());

		if (conta == null) {
			return new BaseResponse(400, "Conta não encontrada!");
		}
		if (request.getTipo() == "") {
			return new BaseResponse(400, "O Tipo da operação não foi preenchido.");
		}
		if (request.getValor() == 0) {
			return new BaseResponse(400, "O valor da operação não foi preenchido.");
		}
		Operacao operacao = new Operacao();
		operacao.setTipo(request.getTipo());
		operacao.setValor(request.getValor());

		switch (request.getTipo()) {

		case "D":
			operacao.setContaDestino(conta);
			break;

		case "S":
			operacao.setContaOrigem(conta);
			break;
		}

		_repository.save(operacao);
		return new BaseResponse(201, "Operacão inserida com sucesso.");
	}

	public double saldo(Long contaId) {

		double saldo = 0;

		List<Operacao> lista = _repository.findOperacoesPorConta(contaId);

		for (Operacao o : lista) {
			switch (o.getTipo()) {
			case "D":
				saldo += o.getValor();
				break;
			case "S":
				saldo -= o.getValor();
				break;
			case "T":
				if (contaId == o.getContaOrigem().getId())
					saldo -= o.getValor();

				if (contaId == o.getContaDestino().getId())
					saldo += o.getValor();

				break;
			default:
				break;
			}

		}

		return saldo;
	}

	public BaseResponse transferencia(TransferenciaRequest transferenciaRequest) {
		BaseResponse response = new BaseResponse();
		Operacao operacao = new Operacao();
		Conta listaDestino = _contaRepository.findByHash(transferenciaRequest.getHashDestino());
		Conta listaOrigem = _contaRepository.findByHash(transferenciaRequest.getHashOrigem());

		if (listaDestino == null) {
			return new BaseResponse(404, "Conta destino não encontrada!");
		}
		if (listaOrigem == null) {
			return new BaseResponse(404, "Conta origem não encontrada!");
		}

		operacao.setContaDestino(listaDestino);
		operacao.setContaOrigem(listaOrigem);
		operacao.setTipo("T");
		operacao.setValor(transferenciaRequest.getValor());

		_repository.save(operacao);
		return new BaseResponse(200, "Transferencia realizada com sucesso!");

	}

}