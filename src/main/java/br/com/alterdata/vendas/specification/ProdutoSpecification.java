package br.com.alterdata.vendas.specification;

import br.com.alterdata.vendas.enums.Role;
import br.com.alterdata.vendas.model.Produto;
import br.com.alterdata.vendas.model.Usuario;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProdutoSpecification {

    public static Specification<Produto> search(String text, Usuario usuario) {
        return new Specification<Produto>() {

            private static final long serialVersionUID = 1L;



            @Override
            public Predicate toPredicate(Root<Produto> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (text == null || text.trim().length() <= 0) return null;

                String likeTerm = "%" + text + "%";

                Predicate predicate;
                if(usuario.getRole().equals(Role.ADMINISTRADOR)){
                    predicate  = criteriaBuilder.or(criteriaBuilder.like(root.get("nome"), likeTerm),
                            criteriaBuilder.or(criteriaBuilder.like(root.get("descricao"), likeTerm),
                                    criteriaBuilder.or(criteriaBuilder.like(root.get("referencia"), likeTerm),
                                            criteriaBuilder.or(criteriaBuilder.like(root.get("categoria").as(String.class), likeTerm)))));
                }else {
                    predicate = criteriaBuilder.or(criteriaBuilder.equal(root.get("nome"), text),
                            criteriaBuilder.or(criteriaBuilder.equal(root.get("descricao"), text),
                                    criteriaBuilder.or(criteriaBuilder.equal(root.get("referencia"), text),
                                            criteriaBuilder.or(criteriaBuilder.equal(root.get("categoria").as(String.class), text)))));
                }
                return predicate;
            }
        };
    }
}
