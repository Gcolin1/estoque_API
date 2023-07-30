package com.estoque.estoque.Controllers;

import com.estoque.estoque.Produto.ListaProdutoDTO;
import com.estoque.estoque.Produto.Produto;
import com.estoque.estoque.Produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ListaProdutoDTO> getAll(){
        List<ListaProdutoDTO> productList = repository.findAll().stream().map(ListaProdutoDTO::new).toList();
        return productList;
    }

}
