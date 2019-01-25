package br.com.alura.forum.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Curso {

	@Id
	private String nome;
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", foreignKey = @ForeignKey(name = "curso_cateforia_fk"))
	private Categoria subcategoria;
	

	public Curso(String nome, Categoria subcategoria) {
		this.nome = nome;
		this.subcategoria = subcategoria;
	}



}
