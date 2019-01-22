package br.com.alura.forum.dto.out;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.entity.Topico;
import br.com.alura.forum.entity.TopicoSituacao;
import br.com.alura.forum.util.DataUtil;
import lombok.Data;

@Data
public class TopicoSaidaDTO {

	private Long id;
	private String pequenaDescricao;
	private long segundosDesdeDaUltimaAtualizacao;
	private String nomeDoProprietario;
	private String nomeDoCurso;
	private String nomeDaSubcategoria;
	private String nomeDaCategoria;
	private boolean resolvido;
	private String titulo;
	private String criadoEm;

	public TopicoSaidaDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.pequenaDescricao = topico.getDetalhes();
		this.segundosDesdeDaUltimaAtualizacao = getSegundosDesdeDaUltimaAtualizacao(topico.getUltimaAtualizacao());
		this.nomeDoProprietario = topico.getUsuarioForum().getNome();
		this.nomeDoCurso = topico.getCurso().getNome();
		this.nomeDaSubcategoria = topico.getCurso().getSubcategoria().getName();
		this.nomeDaCategoria = topico.getCurso().getSubcategoria().getCategoriaPai().getName();
		this.criadoEm = topico.getCriadoEm().format(DataUtil.FORMATADOR) ;
		this.resolvido = TopicoSituacao.RESOLVIDO.equals(topico.getSituacao());
	
	}
	
	public static List<TopicoSaidaDTO> listaApartirDeTopicos(List<Topico> topicos){ 
		return topicos.stream().map(TopicoSaidaDTO::new).collect(Collectors.toList());
	}


	public long getSegundosDesdeDaUltimaAtualizacao(Instant ultimaAtualizacao) {
		return Duration.between(ultimaAtualizacao, Instant.now()).get(ChronoUnit.SECONDS);
	}

	

}
