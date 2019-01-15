package br.com.alura.forum.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alura.forum.annotation.Get;
import br.com.alura.forum.annotation.Rest;
import br.com.alura.forum.dto.input.TopicoEntrdaDeBuscaDTO;
import br.com.alura.forum.dto.out.TopicoSaidaDTO;
import br.com.alura.forum.repository.TopicoRepository;
import br.com.alura.forum.util.DataUtil;

@Rest("api")
public class TopicoController {

	@Autowired
	private TopicoRepository topicoRepository;

	@Get("topicos")
	public ResponseEntity<List<TopicoSaidaDTO>> listarTopicos(TopicoEntrdaDeBuscaDTO buscarTopico) {
		return new ResponseEntity<>(
				TopicoSaidaDTO.listaApartirDeTopicos(topicoRepository.findAll(buscarTopico.criarSpecification())),
				HttpStatus.OK);
	}

	@Get("topicosPorTitulo")
	public ResponseEntity<List<TopicoSaidaDTO>> topicosPorTitulo(String topicos) {
		return new ResponseEntity<>(TopicoSaidaDTO.listaApartirDeTopicos(topicoRepository.topicosPorTitulo(topicos)),
				HttpStatus.OK);
	}

	@Get("topicosPorData")
	public ResponseEntity<List<TopicoSaidaDTO>> findBeteenDate(
			@RequestParam(value = "de", required = false) String dataInicial,
			@RequestParam(value = "ate", required = false) String dataFinal) {

		if (dataFinal == null)
			dataFinal = DataUtil.dataMaxima();

		if (dataInicial == null)
			dataInicial = DataUtil.dataMinima();

		var de = LocalDate.parse(dataInicial, DataUtil.FORMATADOR);
		var ate = LocalDate.parse(dataFinal, DataUtil.FORMATADOR);

		return new ResponseEntity<>(
				TopicoSaidaDTO.listaApartirDeTopicos(topicoRepository.findByCreatedIn_Between(de, ate)), HttpStatus.OK);
	}

	@Get("countTopicUser")
	public ResponseEntity<Long> countTopicUser(String user) {
		return new ResponseEntity<>(topicoRepository.countByUserForum_Name(user), HttpStatus.OK);
	}

}
