package br.com.alura.forum.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.alura.forum.entity.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.UsuarioRepository;
import lombok.Data;

@Data
public class TopicoNovoDTO {


	@NotBlank
	private String cursoNome;
	@Size(min = 10, max = 200)
	private String detalhes;
	@Size(min = 5, max = 100)
	private String titulo;
	
	public Topico criaTopico( String email,UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
		
		var curso = cursoRepository.findByNome(cursoNome);
		var usuario = usuarioRepository.findByEmail(email);
		
		return new Topico(titulo, detalhes, usuario, curso);
	}
	
	
}
