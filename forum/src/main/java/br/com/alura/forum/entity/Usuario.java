package br.com.alura.forum.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Usuario {

	@Id
	private String email;
	private String nome;
	private LocalDate criadoEm;

	public Usuario(String nome, String email) {
		this.nome = nome;
		this.email = email;
		this.criadoEm = LocalDate.now();
	}
	
	public Usuario(String nome, String email, LocalDate criadoEm) {
		this.nome = nome;
		this.email = email;
		this.criadoEm = criadoEm;
	}

	

}
