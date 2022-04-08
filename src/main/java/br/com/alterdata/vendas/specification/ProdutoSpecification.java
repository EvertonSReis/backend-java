package br.com.alterdata.vendas.specification;

import br.com.alterdata.vendas.model.Produto;
import br.com.alterdata.vendas.model.Usuario;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProdutoSpecification {

    public static Specification<Produto> search(String text){
        return new Specification<Produto>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Produto> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (text == null || text.trim().length() <= 0) return null;

                String likeTerm = "%" + text + "%";

                Predicate predicate = criteriaBuilder.or(criteriaBuilder.like(root.get("nome"), likeTerm),
                                criteriaBuilder.or(criteriaBuilder.like(root.get("descricao"), likeTerm),
                                criteriaBuilder.or(criteriaBuilder.like(root.get("referencia"), likeTerm),
                                criteriaBuilder.or(criteriaBuilder.like(root.get("categoria").as(String.class), likeTerm)))));

                return predicate;
            }
        };
    }
}
