package com.estoque.estoque.Produto;

public record dadosCadastroProdutosDTO(
        String nome,
        String categoria,
        String descricao,
        String medida,
        Integer estoque,
        String fornecedor,
        Integer precoCusto,
        Integer precoVenda
) {

}
