package com.estoque.estoque.Produto;

public record ListaProdutoDTO(Long id, String nome, String categoria, String descricao, String medida, Integer estoque, String fornecedor, Integer preco_custo, Integer preco_venda) {
    public ListaProdutoDTO(Produto produto){
        this(
                produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getDescricao(),
                produto.getMedida(),
                produto.getEstoque(),
                produto.getFornecedor(),
                produto.getPreco_custo(),
                produto.getPreco_venda());
    }
}
