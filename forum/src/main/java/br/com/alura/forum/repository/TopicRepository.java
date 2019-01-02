package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import br.com.alura.forum.entity.Topic;

public interface TopicRepository extends CrudRepository<Topic, Long> , JpaSpecificationExecutor<Topic>{

	List<Topic> findAll();
}
