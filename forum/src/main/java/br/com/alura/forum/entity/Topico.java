package br.com.alura.forum.entity;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
    @JoinColumn(name = "usuario_forum_id")
	private Usuario usuarioForum;
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
	private Curso curso;
	private Instant ultimaAtualizacao;
	private LocalDate criadoEm;
	private int numeroDePerguntas;
	private TopicoSituacao situacao;

	public Topico(String titulo, String detailhes, Usuario user, Curso curso) {
	
		this.titulo = titulo;
		this.detalhes = detailhes;
		this.usuarioForum = user;
		this.curso = curso;
		this.ultimaAtualizacao = Instant.now();
		this.criadoEm = LocalDate.now();
		this.situacao = TopicoSituacao.NAO_RESOLVIDO;
	}

}