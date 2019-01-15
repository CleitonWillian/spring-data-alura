package br.com.alura.forum.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

import br.com.alura.forum.entity.Topico;

public interface TopicoRepository extends CrudRepository<Topico, Long> , JpaSpecificationExecutor<Topico>{

	List<Topico> findAll();
	
	List<Topico> findAll(Sort sort);
	
	//Query Method
	List<Topico> findByTitulo(String titulo);
	
	//Query JDBC
	@Query(value = "select * from topico t where t.titulo = ?1", nativeQuery = true )
	List<Topico> topicosPorTituloJDBC(String titulo);
	
	//Query JPQL
	@Query(value = "select object(t) from Topico as t where t.titulo = ?1")
	List<Topico> topicosPorTituloJPQL(String titulo);
	
	Long countByUsuarioForum_Nome(String nome);
	
	List<Topico>findByCriadoEm_Between(LocalDate dataInicial, LocalDate dataFinal);
	
	List<Topico> findFirst3ByTituloOrderById(String titutlo);
	
	
}
