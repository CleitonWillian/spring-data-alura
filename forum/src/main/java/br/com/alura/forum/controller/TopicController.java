package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.dto.input.TopicSearchInputDTO;
import br.com.alura.forum.dto.out.TopicOutDTO;
import br.com.alura.forum.entity.Category;
import br.com.alura.forum.entity.Course;
import br.com.alura.forum.entity.Topic;
import br.com.alura.forum.entity.User;
import br.com.alura.forum.repository.TopicRepository;

@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
public class TopicController {

	@Autowired
	private TopicRepository topicRepository;

	@GetMapping("topics")
	@ResponseBody
	public ResponseEntity<List<TopicOutDTO>> listTopic(TopicSearchInputDTO topicSearch) {
		return new ResponseEntity<>(TopicOutDTO.listFromTopics(topicRepository.findAll(topicSearch.build())),
				HttpStatus.OK);

	}

	//Popular o banco de dados
	@GetMapping(path = "criaMock", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public ResponseEntity<String> criaMock() {
		Category subcategory = new Category("Java", new Category("Programacao"));

		Course course1 = new Course("Java e JSF", subcategory);
		Course course2 = new Course("Spring", subcategory);

		Topic topic1 = new Topic("Problemas com JSF", "Erro ao fazer conversão da data",
				new User("Fulano", "fulano@gmail.com", "12345"), course1);

		Topic topic2 = new Topic("Problemas com JSF", "Erro com Taglib",
				new User("Beltrano", "beltrano@gmail.com", "654321"), course1);

		Topic topic3 = new Topic("Problema com SpringBoot", "Erro ao criar um Bean",
				new User("Ciclano", "ciclano@gmail.com", "654321"), course2);

		topicRepository.save(topic1);
		topicRepository.save(topic2);
		topicRepository.save(topic3);

		return new ResponseEntity<>("ok", HttpStatus.OK);
	}

}
