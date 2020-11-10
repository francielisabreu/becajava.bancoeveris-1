package br.bancoeveris.app.request;

import java.util.List;

import br.bancoeveris.app.model.*;
import br.bancoeveris.app.response.BaseResponse;

public class ClienteList extends BaseResponse {
	
	private List<Cliente> Clientes;

	public List<Cliente> getClientes() {
		return Clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		Clientes = clientes;
	}

}