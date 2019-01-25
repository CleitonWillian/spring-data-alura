package br.com.alura.forum.entity;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.alura.forum.util.TopicSituacaoConverter;

import javax.persistence.ForeignKey;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String titulo;
	private String detalhes;
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_forum_id", nullable = false, foreignKey = @ForeignKey(name = "topico_usuario_fk"))
	private Usuario usuarioForum;
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id" , nullable = false, foreignKey = @ForeignKey(name = "topico_curso_fk"))
	private Curso curso;
	private Instant ultimaAtualizacao;
	private LocalDate criadoEm;
	@Convert(converter = TopicSituacaoConverter.class)
	private TopicoSituacao situacao;

	
	public Topico(String titulo, String detalhes, Usuario usuario, Curso curso) {
	
		this.titulo = titulo;
		this.detalhes = detalhes;
		this.usuarioForum = usuario;
		this.curso = curso;
		this.ultimaAtualizacao = Instant.now();
		this.criadoEm = LocalDate.now();
		this.situacao = TopicoSituacao.NAO_RESOLVIDO;
	}

}