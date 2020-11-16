package br.bancoeveris.app.response;

public class OperacaoResponse {
	 	private Long id;
	    private String tipo;
	    private double valor;
		
	    public OperacaoResponse() {
	    	
	    }
	    public OperacaoResponse(Long id) {
			super();
			this.id = id;
		}
		public OperacaoResponse(String tipo, double valor) {
			super();
			this.tipo = tipo;
			this.valor = valor;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		public double getValor() {
			return valor;
		}
		public void setValor(double valor) {
			this.valor = valor;
		}	    
}
