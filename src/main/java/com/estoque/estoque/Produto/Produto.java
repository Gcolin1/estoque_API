package com.estoque.estoque.Produto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "produtos")
@Entity(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String categoria;
    private String descricao;
    private String medida;
    private Integer estoque;
    private String fornecedor;
    private Integer preco_custo;
    private Integer preco_venda;

    public Produto(dadosCadastroProdutosDTO dados){
        this.nome = dados.nome();
        this.categoria = dados.categoria();
        this.descricao = dados.descricao();
        this.medida = dados.medida();
        this.estoque = dados.estoque();
        this.fornecedor = dados.fornecedor();
        this.preco_custo = dados.precoCusto();
        this.preco_venda = dados.precoVenda();
    }



}
