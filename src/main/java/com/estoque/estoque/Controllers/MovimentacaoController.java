package com.estoque.estoque.Controllers;

import com.estoque.estoque.Movimentacao.ListaMovimentacaoDTO;
import com.estoque.estoque.Movimentacao.Movimentacao;
import com.estoque.estoque.Movimentacao.MovimentacaoRepository;
import com.estoque.estoque.Movimentacao.dadosCadastroMovimentacaoDTO;
import com.estoque.estoque.Produto.Produto;
import com.estoque.estoque.Produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    MovimentacaoRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ListaMovimentacaoDTO> getAll(){
        List<ListaMovimentacaoDTO> movimentacaoList = repository.findAll().stream().map(ListaMovimentacaoDTO::new).toList();
        return movimentacaoList;
    }

    //RETIRADA DE ESTOQUE
    @PutMapping("/{id}")
    public ResponseEntity<Produto> RetirarEstoque(@PathVariable Long id, @RequestBody dadosCadastroMovimentacaoDTO dados){
        Optional<Produto> ProdutoExistenteOptional = produtoRepository.findById(id);

        if(!ProdutoExistenteOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        String id_produto = id.toString();

        //salvando o quanto foi retirado
        Movimentacao movimentacao = new Movimentacao(dados, id_produto);

        repository.save(movimentacao);

        //retirando do estoque a quantidade selecionada
        Produto produtoExistente = ProdutoExistenteOptional.get();
        produtoExistente.setEstoque(produtoExistente.getEstoque() - dados.estoque());

        Produto produtoAtualizadoSalvo = produtoRepository.save(produtoExistente);
        return ResponseEntity.ok(produtoAtualizadoSalvo);


    }

    //REPOR ESTOQUE
    @PutMapping("repor/{id}")
    public ResponseEntity<Produto> AdicionarEstoque(@PathVariable Long id, @RequestBody dadosCadastroMovimentacaoDTO dados){
        Optional<Produto> ProdutoExistenteOptional = produtoRepository.findById(id);

        if(!ProdutoExistenteOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        String id_produto = id.toString();

        Movimentacao movimentacao = new Movimentacao(dados, id_produto);
        repository.save(movimentacao);

        Produto produtoExistente = ProdutoExistenteOptional.get();

        produtoExistente.setEstoque(produtoExistente.getEstoque() + dados.estoque());

        Produto produtoAtualizadoSalvo = produtoRepository.save(produtoExistente);

        return ResponseEntity.ok(produtoAtualizadoSalvo);
    }

}
