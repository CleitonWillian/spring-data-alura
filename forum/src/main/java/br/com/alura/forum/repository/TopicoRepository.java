package br.com.alura.forum.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.alura.forum.entity.Topico;

public interface TopicoRepository extends CrudRepository<Topico, Long> , JpaSpecificationExecutor<Topico>{

	List<Topico> findAll();
	
	List<Topico> findAll(Sort sort);
	
	//Query Method
	List<Topico> findByTitle(String title);
	
	//Query JDBC
	@Query(value = "select * from topic t where t.title = ?1", nativeQuery = true )
	List<Topico> allByTopicTitleNative(String title);
	
	//Query JPQL
	@Query(value = "select object(t) from Topic as t where t.title = ?1")
	List<Topico> topicosPorTitulo(String title);
	
	Long countByUserForum_Name(String name);
	
	List<Topico>findByCreatedIn_Between(LocalDate initialDate, LocalDate finalDate);
	
	List<Topico> findFirst3ByTitleOrderById(String title);
	
	
}
