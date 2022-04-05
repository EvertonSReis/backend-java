package br.com.alterdata.vendas.service;

import br.com.alterdata.vendas.enums.Categorias;
import br.com.alterdata.vendas.model.Produto;
import br.com.alterdata.vendas.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class ProdutoService {

    @Autowired private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto){
        Produto salvarProduto = produtoRepository.save(produto);
        return salvarProduto;
    }

    public Produto update(Produto produto){
        Produto updateProduto = produtoRepository.save(produto);
        return updateProduto;
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto listarPorId(Long id){
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.get();
    }

    public void delete(Long id){
        produtoRepository.deleteById(id);
    }

    public int updateCategoria(Produto produto){
        return produtoRepository.updateCategoria(produto.getId(), produto.getCategoria());
    }
}
