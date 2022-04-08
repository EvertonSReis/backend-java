package br.com.alterdata.vendas.service;

import br.com.alterdata.vendas.enums.Categorias;
import br.com.alterdata.vendas.model.Produto;
import br.com.alterdata.vendas.model.Usuario;
import br.com.alterdata.vendas.model.model.PageModel;
import br.com.alterdata.vendas.model.model.PageRequestModel;
import br.com.alterdata.vendas.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

import br.com.alterdata.vendas.security.AccessManager;
import br.com.alterdata.vendas.specification.ProdutoSpecification;
import br.com.alterdata.vendas.specification.UsuarioSpecification;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    AccessManager accessManager;

    public Produto salvar(Produto produto) {
        Produto salvarProduto = produtoRepository.save(produto);
        return salvarProduto;
    }

    public Produto update(Produto produto) {
        Produto updateProduto = produtoRepository.save(produto);
        return updateProduto;
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto listarPorId(Long id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.get();
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    public int updateCategoria(Produto produto) {
        return produtoRepository.updateCategoria(produto.getId(), produto.getCategoria());
    }

    @SneakyThrows
    public PageModel<Produto> listAllOnLazyModel(PageRequestModel pr) {
        Pageable pageable = pr.toSpringPageRequest();
        ;
        Usuario usuario = accessManager.obterUsuarioLogado();
        Specification<Produto> specification = ProdutoSpecification.search(pr.getSearch(), usuario);

        Page<Produto> page = produtoRepository.findAll(specification, pageable);

        PageModel<Produto> pm = new PageModel<>((int) page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
        return pm;
    }
}
