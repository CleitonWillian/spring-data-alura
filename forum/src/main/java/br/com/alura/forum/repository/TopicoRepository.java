package br.com.alura.forum.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.forum.entity.Topico;
import br.com.alura.forum.entity.Usuario;

@Transactional
public interface TopicoRepository extends CrudRepository<Topico, Long> , JpaSpecificationExecutor<Topico>{

	List<Topico> findAll();
	
	
	//Query Method
	List<Topico> findByTitulo(String titulo);
	
	//Query JDBC
	@Query(value = "select * from topico t where t.titulo = ?1", nativeQuery = true )
	List<Topico> topicosPorTituloJDBC(String titulo);
	
	//Query JPQL
	@Query(value = "select object(t) from Topico as t where t.titulo = ?1")
	List<Topico> topicosPorTituloJPQL(String titulo);
	
	Long countByUsuarioForum_Email(String nome);
	
	//Query entre datas
	List<Topico>findByCriadoEm_Between(LocalDate dataInicial,LocalDate dataFinal);
	
	List<Topico> findFirst3ByTituloOrderById(String titutlo);
	
	List<Topico> findByUsuarioForumAndCriadoEmAfterOrderByCriadoEm(Usuario usuarioForum, LocalDate diaCriado);

	//Query update
	@Modifying
	@Query("update Topico t set t.titulo = ?1 where t.id = ?2 and t.usuarioForum.email = ?3")
	int setTituloById(String titulo, long id, String email);
	
	@Procedure("abandonarTopicos")
	String abandonarTopicosAntesDe(LocalDate data);
	
	
	
	
}
