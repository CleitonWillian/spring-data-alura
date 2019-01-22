package br.com.alura.forum.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.alura.forum.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Usuario findByEmail(String email);
	
}
