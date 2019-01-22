package br.com.alura.forum.dto.input;

import java.util.ArrayList;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.forum.entity.Topico;
import br.com.alura.forum.entity.TopicoSituacao;
import lombok.Data;

@Data
public class TopicoEntradaDeBuscaDTO {

	private TopicoSituacao situacao;
	private String categoriaNome;
	private String cursoNome;
	private String descricao;

	public Specification<Topico> criarSpecification() {
		return (root, query, criteriaBuilder) -> {
			
			var predicato = new ArrayList<Predicate>();

			if (situacao != null) {
				predicato.add(criteriaBuilder.equal(root.get("situacao"), situacao));
			}
			
			if (categoriaNome != null) {
				var rootPath = root.get("curso").get("subcategoria");
				Path<String> categoriaNomePath = rootPath.get("categoriaPai").get("nome");
				Path<String> subcategoriaNamePath = rootPath.get("nome");
				
				predicato.add(criteriaBuilder.or(criteriaBuilder.like(categoriaNomePath, categoriaNome),
						criteriaBuilder.like(subcategoriaNamePath, categoriaNome)));
			}
			
			if(descricao != null) {
				Path<String> detalhesPath = root.get("detalhes");
				predicato.add(criteriaBuilder.like(criteriaBuilder.upper(detalhesPath), 
						"%"+descricao.toUpperCase()+"%"));
			}
			
			
			if(cursoNome != null) {
				Path<String> cursoNomePath = root.get("curso").get("nome");
				predicato.add(criteriaBuilder.like(cursoNomePath, "%"+cursoNome+"%"));
			}

			return criteriaBuilder.and(predicato.toArray(new Predicate[0]));
		};
	}

}
