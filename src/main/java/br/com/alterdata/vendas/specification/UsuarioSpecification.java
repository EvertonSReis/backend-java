package br.com.alterdata.vendas.specification;

import br.com.alterdata.vendas.model.Usuario;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UsuarioSpecification {

    public static Specification<Usuario> search(String text){
        return new Specification<Usuario>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (text == null || text.trim().length() <= 0) return null;

                String likeTerm = "%" + text + "%";

                Predicate predicate = criteriaBuilder.or(criteriaBuilder.like(root.get("role").as(String.class), likeTerm));

                return predicate;
            }
        };
    }
}
