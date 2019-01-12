package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.alura.forum.annotation.Get;
import br.com.alura.forum.annotation.Rest;
import br.com.alura.forum.dto.input.TopicSearchInputDTO;
import br.com.alura.forum.dto.out.TopicOutDTO;
import br.com.alura.forum.entity.Category;
import br.com.alura.forum.entity.Course;
import br.com.alura.forum.entity.Topic;
import br.com.alura.forum.entity.TopicStatus;
import br.com.alura.forum.entity.User;
import br.com.alura.forum.repository.TopicRepository;

@Rest("api")
public class TopicController {

	@Autowired
	private TopicRepository topicRepository;
	
	@Get("topics")
	public ResponseEntity<List<TopicOutDTO>> listTopic(TopicSearchInputDTO topicSearch) {
		return new ResponseEntity<>(TopicOutDTO.listFromTopics(topicRepository.findAll(topicSearch.build())),
				HttpStatus.OK);
	}
	
	@Get("findByTitle")
	 public ResponseEntity<List<TopicOutDTO>>findByTitle(String title){
		return new ResponseEntity<>(TopicOutDTO.listFromTopics(topicRepository.allByTopicTitleJpql(title)),HttpStatus.OK);
		
	}

	@Get("countTopicUser")
	public ResponseEntity<Long>countTopicUser(String user){
		return new ResponseEntity<>(topicRepository.countByUserForum_Name(user),HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	// Popular o banco de dados
	@Get(path = "criaMock", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> criaMock() {
		try {
			var subcategory = new Category("Java", new Category("Programacao"));

			var course1 = new Course("Java e JSF", subcategory);
			var course2 = new Course("Spring", subcategory);
			var fulano = new User("Fulano", "fulano@gmail.com", "12345");
			
			var topic1 = new Topic("Problemas com JSF", "Erro ao fazer conversão da data",
					fulano, course1);

			var topic2 = new Topic("Erro no JSF", "Erro com Taglib",
					new User("Beltrano", "beltrano@gmail.com", "654321"), course1);
			topic2.setStatus(TopicStatus.SOLVED);

			var topic3 = new Topic("Problema com SpringBoot", "Erro ao criar um Bean",
					new User("Ciclano", "ciclano@gmail.com", "654321"), course2);

			var topic4 = new Topic("JWT no SpringBoot", "Configuracao do JWT no Spring Boot",
					fulano, course1);

			var topic5 = new Topic("Erro no SpringBoot", "Erro ao criar controller",
					new User("Joana", "joana@gmail.com", "102938"), course2);
			topic5.setStatus(TopicStatus.SOLVED);

			var topic6 = new Topic("SpringBoot não sobe", "Application failed, erro no data source",
					new User("Aline", "aline@gmail.com", "832910"), course2);
			topic6.setStatus(TopicStatus.SOLVED);

			topicRepository.saveAll(Arrays.asList(topic1, topic2, topic3, topic4, topic5, topic6));
			return new ResponseEntity<>("ok", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
