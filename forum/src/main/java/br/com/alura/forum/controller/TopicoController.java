package br.com.alura.forum.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.dto.input.TopicoEntradaDeBuscaDTO;
import br.com.alura.forum.dto.input.TopicoNovoDTO;
import br.com.alura.forum.dto.out.TopicoSaidaDTO;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import br.com.alura.forum.repository.UsuarioRepository;
import br.com.alura.forum.util.DataUtil;

@RestController
@RequestMapping(path = "api/topicos" , produces = MediaType.APPLICATION_JSON_VALUE)
public class TopicoController {

	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public ResponseEntity<List<TopicoSaidaDTO>> listarTopicos(TopicoEntradaDeBuscaDTO buscarTopico) {
		var todosOsTopicos = topicoRepository.findAll(buscarTopico.criarSpecification());
		return new ResponseEntity<>(TopicoSaidaDTO.listaApartirDeTopicos(todosOsTopicos), HttpStatus.OK);
	}

	@GetMapping("porTituloJPQL")
	public ResponseEntity<List<TopicoSaidaDTO>> topicosPorTituloJPQL(String titulo) {
		var topicosPorTituloJPQL = topicoRepository.topicosPorTituloJPQL(titulo);
		return new ResponseEntity<>(TopicoSaidaDTO.listaApartirDeTopicos(topicosPorTituloJPQL), HttpStatus.OK);
	}
	
	@GetMapping("porTituloJDBC")
	public ResponseEntity<List<TopicoSaidaDTO>> topicosPorTituloJDBC(String titulo) {
		var topicosPorTituloJDBC = topicoRepository.topicosPorTituloJDBC(titulo);
		return new ResponseEntity<>(TopicoSaidaDTO.listaApartirDeTopicos(topicosPorTituloJDBC), HttpStatus.OK);
	}

	

	@GetMapping("porData")
	public ResponseEntity<List<TopicoSaidaDTO>> topicosPorData(
			@RequestParam(value = "de", required = false) String dataInicial,
			@RequestParam(value = "ate", required = false) String dataFinal) {

		var de = DataUtil.validaERetornaDataMinimaFormatada(dataInicial);
		var ate = DataUtil.validaERetornaDataMaximaFormatada(dataFinal);
		var topicosEntreAData = topicoRepository.findByCriadoEm_Between(de, ate);
		return new ResponseEntity<>(
				TopicoSaidaDTO.listaApartirDeTopicos(topicosEntreAData), HttpStatus.OK);
	}

	@GetMapping("{email}/quantdadePorUsuario")
	public ResponseEntity<Long> quantosTopicosPossuiOUsuario(String email) {
		return new ResponseEntity<>(topicoRepository.countByUsuarioForum_Email(email), HttpStatus.OK);
	}

	@GetMapping("{email}/apartirDe")
	public ResponseEntity<List<TopicoSaidaDTO>> topicosPosUsuarioApartirDe(@RequestParam("email") String email,
			String dataInicial) {

		var de = DataUtil.validaERetornaDataMinimaFormatada(dataInicial);
		var usuario = usuarioRepository.findByEmail(email);
		var topicosApartirDe = topicoRepository.findByUsuarioForumAndCriadoEmAfterOrderByCriadoEm(usuario, de);

		return new ResponseEntity<>(TopicoSaidaDTO.listaApartirDeTopicos(topicosApartirDe), HttpStatus.OK);
	}

	@PostMapping("{email}/novo")
	public ResponseEntity<String> novoTopico(@RequestParam("email") String email,
			@Valid @RequestBody TopicoNovoDTO novoTopico) {
		var topicoEntidade = novoTopico.criaTopico(email, usuarioRepository, cursoRepository);
		topicoRepository.save(topicoEntidade);
		return ResponseEntity.accepted().build();

	}
	
	@PutMapping("{email}/titulo")
	public ResponseEntity<Integer> atualizaTitulo(@RequestParam("email")String email,String titulo, int id) {
		return new ResponseEntity<>(topicoRepository.setTituloById(titulo, id, email), HttpStatus.OK);
	}
	
	@PutMapping(path = "abandonarTopicos", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> abandonarTopicos(String data) {
		var apartirDe = DataUtil.validaERetornaDataMinimaFormatada(data);
		return  ResponseEntity.accepted().body(topicoRepository.abandonarTopicosAntesDe(apartirDe));
	}

}
