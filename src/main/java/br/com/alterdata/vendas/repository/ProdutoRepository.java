package br.com.alterdata.vendas.repository;

import br.com.alterdata.vendas.enums.Categorias;
import br.com.alterdata.vendas.model.Produto;
import br.com.alterdata.vendas.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto>{

    @Transactional(readOnly = false)
    @Modifying
    @Query("UPDATE Produto SET categoria = ?2 WHERE id = ?1")
    public int updateCategoria(Long id, Categorias categoria);

}
