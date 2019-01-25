package br.com.alura.forum.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
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
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_pai_id", nullable=true, foreignKey = @ForeignKey(name = "categoria_pai_fk"))
	private Categoria categoriaPai;

	public Categoria(String name, Categoria categoria) {
		this.name = name;
		this.categoriaPai = categoria;
	}

	public Categoria(String name) {
		this.name = name;

	}



}
