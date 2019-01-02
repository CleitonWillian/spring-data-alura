package br.com.alura.forum.dto.input;

import java.util.ArrayList;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.forum.entity.Topic;
import br.com.alura.forum.entity.TopicStatus;
import lombok.Data;

@Data
public class TopicSearchInputDTO {

	private TopicStatus status;
	private String categoryName;

	public Specification<Topic> build() {
		return (root, query, criteriaBuilder) -> {
			ArrayList<Predicate> predicates = new ArrayList<>();

			if (status != null) {
				predicates.add(criteriaBuilder.equal(root.get("status"), status));
			}
			if (categoryName != null) {
				Path<String> rootPath = root.get("course").get("subcategory");
				Path<String> categoryNamePath = rootPath.get("parentCategory").get("name");
				Path<String> subcategoryNamePath = rootPath.get("name");
				
				predicates.add(criteriaBuilder.or(criteriaBuilder.like(categoryNamePath, categoryName),
						criteriaBuilder.like(subcategoryNamePath, categoryName)));
			
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
