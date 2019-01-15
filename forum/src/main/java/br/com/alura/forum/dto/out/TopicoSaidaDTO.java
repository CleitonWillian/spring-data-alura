package br.com.alura.forum.dto.out;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.entity.Topic;
import br.com.alura.forum.entity.TopicStatus;
import lombok.Data;

@Data
public class TopicOutDTO {

	private Long id;
	private String shortDescription;
	private long secondsSinceLastUpdate;
	private String ownerName;
	private String courseName;
	private String subcategoryName;
	private String categoryName;
	private int numberOfResponses;
	private boolean solved;
	private String title;

	public TopicOutDTO(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.shortDescription = topic.getDetails();
		this.secondsSinceLastUpdate = getSecondsSinceLastUpdate(topic.getLastUpdate());
		this.ownerName = topic.getUserForum().getName();
		this.courseName = topic.getCourse().getName();
		this.subcategoryName = topic.getCourse().getSubcategory().getName();
		this.categoryName = topic.getCourse().getSubcategory().getParentCategory().getName();
		this.numberOfResponses = topic.getNumberOfAnswers();
		this.solved = TopicStatus.SOLVED.equals(topic.getStatus());
	
	}
	
	public static List<TopicOutDTO> listFromTopics(List<Topic> topics){ 
		return topics.stream().map(TopicOutDTO::new).collect(Collectors.toList());
	}


	public long getSecondsSinceLastUpdate(Instant lastUpdate) {
		return Duration.between(lastUpdate, Instant.now()).get(ChronoUnit.SECONDS);
	}

	

}
