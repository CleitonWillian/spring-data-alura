package br.com.alura.forum.entity;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private String details;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
	private User user;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
	private Course course;
	private Instant lastUpdate;
	private int numberOfAnswers;
	private TopicStatus status;

	public Topic(String title, String details, User user, Course course) {
		this.title = title;
		this.details = details;
		this.user = user;
		this.course = course;
		this.lastUpdate = Instant.now();
		this.status = TopicStatus.UNSOLVED;
	}

}