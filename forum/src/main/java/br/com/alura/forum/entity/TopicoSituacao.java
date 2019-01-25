
package br.com.alura.forum.entity;

import java.util.Arrays;

public enum TopicoSituacao {

	RESOLVIDO("resolvido"),
	NAO_RESOLVIDO("nao resolvido"),
	ABANDONADO("abandonado");
	
	private final String situacao;

	
	TopicoSituacao(String situacao){
		this.situacao = situacao;
	}

	public String getSituacao() {
		return situacao;
	}
	
	public static TopicoSituacao fromBancoDeDados(String situacao) {

		return Arrays.stream(TopicoSituacao.values())
		.filter(t -> t.getSituacao().equals(situacao))
		.findFirst()
		.orElseThrow();
		
			
		
		
	
		
	
		
		
	}
	
	
}
