package br.com.geremiasemerim.produtos.controller;

import br.com.geremiasemerim.produtos.model.Produto;
import br.com.geremiasemerim.produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getTodosProdutos(){
        List<Produto> produtos = produtoRepository.findAll();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto){
        Produto produtoSalvo = produtoRepository.save(produto);
        return ResponseEntity.ok(produtoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(
            @PathVariable Long id,
            @RequestBody Produto produtoAtualizado) {

        Optional<Produto> possivelProduto = produtoRepository.findById(id);

        if (possivelProduto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Produto produtoAntigo = possivelProduto.get();
        produtoAntigo.setNome(produtoAtualizado.getNome());
        produtoAntigo.setPreco(produtoAtualizado.getPreco());
        produtoAntigo.setQuantidade(produtoAtualizado.getQuantidade());

        Produto produtoSalvo = produtoRepository.save(produtoAntigo);
        return ResponseEntity.ok(produtoSalvo);
    }

    @DeleteMapping("produtos/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        if (produtoRepository.existsById(id)) { //verifica se o produto existe no banco de dados?
            produtoRepository.deleteById(id); // exclui o item de acordo com o id.
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}

