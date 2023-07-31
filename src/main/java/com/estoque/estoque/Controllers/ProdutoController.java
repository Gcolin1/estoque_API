package com.estoque.estoque.Controllers;

import com.estoque.estoque.Produto.ListaProdutoDTO;
import com.estoque.estoque.Produto.Produto;
import com.estoque.estoque.Produto.ProdutoRepository;
import com.estoque.estoque.Produto.dadosCadastroProdutosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    //LISTAR TODOS OS PRODUTOS
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ListaProdutoDTO> getAll(){
        List<ListaProdutoDTO> productList = repository.findAll().stream().map(ListaProdutoDTO::new).toList();
        return productList;
    }

    //CRIAR PRODUTO
    @PostMapping
    public void CreateProduct(@RequestBody dadosCadastroProdutosDTO dados){
        Produto produtoDados = new Produto(dados);
        repository.save(produtoDados);
        return;
    }

    //RETIRADA DE ESTOQUE
    @PutMapping("/{id}")
    public ResponseEntity<Produto> RetirarEstoque(@PathVariable Long id, @RequestBody Produto produtoAtualizado){
        Optional<Produto> ProdutoExistenteOptional = repository.findById(id);

        if(!ProdutoExistenteOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Produto produtoExistente = ProdutoExistenteOptional.get();

        produtoExistente.setEstoque(produtoExistente.getEstoque() - produtoAtualizado.getEstoque());

        Produto produtoAtualizadoSalvo = repository.save(produtoExistente);

        return ResponseEntity.ok(produtoAtualizadoSalvo);
    }

    //REPOR ESTOQUE
    @PutMapping("repor/{id}")
    public ResponseEntity<Produto> AdicionarEstoque(@PathVariable Long id, @RequestBody Produto produtoAtualizado){
        Optional<Produto> ProdutoExistenteOptional = repository.findById(id);

        if(!ProdutoExistenteOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Produto produtoExistente = ProdutoExistenteOptional.get();

        produtoExistente.setEstoque(produtoExistente.getEstoque() + produtoAtualizado.getEstoque());

        Produto produtoAtualizadoSalvo = repository.save(produtoExistente);

        return ResponseEntity.ok(produtoAtualizadoSalvo);
    }

}
