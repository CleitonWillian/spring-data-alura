package br.com.alura.forum.dto.input;

import java.util.ArrayList;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.forum.entity.Topico;
import br.com.alura.forum.entity.TopicoSituacao;
import lombok.Data;

@Data
public class TopicoEntrdaDeBuscaDTO {

	private TopicoSituacao status;
	private String categoryName;
	private String courseName;
	private String desc;

	public Specification<Topico> criarSpecification() {
		return (root, query, criteriaBuilder) -> {
			
			var predicates = new ArrayList<Predicate>();

			if (status != null) {
				predicates.add(criteriaBuilder.equal(root.get("status"), status));
			}
			
			if (categoryName != null) {
				var rootPath = root.get("course").get("subcategory");
				Path<String> categoryNamePath = rootPath.get("parentCategory").get("name");
				Path<String> subcategoryNamePath = rootPath.get("name");
				
				predicates.add(criteriaBuilder.or(criteriaBuilder.like(categoryNamePath, categoryName),
						criteriaBuilder.like(subcategoryNamePath, categoryName)));
			}
			
			if(desc != null) {
				Path<String> detailsPath = root.get("details");
				predicates.add(criteriaBuilder.like(criteriaBuilder.upper(detailsPath), 
						"%"+desc.toUpperCase()+"%"));
			}
			
			if(courseName != null) {
				Path<String> courseNamePath = root.get("course").get("name");
				predicates.add(criteriaBuilder.like(courseNamePath, "%"+courseName+"%"));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
