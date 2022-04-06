package br.com.alterdata.vendas.controller;

import br.com.alterdata.vendas.dto.ProdutoUpdateCategoriadto;
import br.com.alterdata.vendas.enums.Categorias;
import br.com.alterdata.vendas.model.Produto;
import br.com.alterdata.vendas.service.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("produtos")
public class ProdutosController {

    @Autowired private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
        Produto obj = produtoService.salvar(produto);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable(name = "id") Long id,
                                          @RequestBody Produto produto){
        produto.setId(id);
        Produto updateProduto = produtoService.update(produto);
        return ResponseEntity.ok(updateProduto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> produtos = produtoService.listar();
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> listarPorId(@PathVariable Long id){
        Produto produto = produtoService.listarPorId(id);
        return ResponseEntity.ok().body(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> delete(@PathVariable Long id){
        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/categoria/{id}")
    public ResponseEntity<?> updateCategoria(@PathVariable(name = "id") Long id,
                                             @RequestBody @Valid ProdutoUpdateCategoriadto produtodto){
        Produto produto = new Produto();
        produto.setId(id);
        produto.setCategoria(produtodto.getCategoria());

         produtoService.updateCategoria(produto);

         return ResponseEntity.ok().build();
    }

}
