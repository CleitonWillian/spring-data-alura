package br.com.alura.forum.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.alura.forum.entity.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long> {

	Curso findByNome(String cursoNome);

	
	
}
