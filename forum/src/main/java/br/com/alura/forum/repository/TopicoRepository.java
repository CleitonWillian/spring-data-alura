package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.alura.forum.entity.Topic;

public interface TopicRepository extends CrudRepository<Topic, Long> , JpaSpecificationExecutor<Topic>{

	List<Topic> findAll();
	
	List<Topic> findByTitle(String title);
	
	@Query(value = "select * from topic t where t.title = ?1", nativeQuery = true )
	List<Topic> allByTopicTitleNative(String title);
	
	@Query(value = "select object(t) from Topic as t where t.title = ?1")
	List<Topic> allByTopicTitleJpql(String title);
	
	Long countByUserForum_Name(String name);
	
	List<Topic> findFirst3ByTitleOrderById(String title);
}
