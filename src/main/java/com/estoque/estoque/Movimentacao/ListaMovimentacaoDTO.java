package com.estoque.estoque.Movimentacao;

public record ListaMovimentacaoDTO(Long id, String id_produto ,Integer estoque, String destino, String motivo, String responsavel, String data, String hora) {
    public ListaMovimentacaoDTO(Movimentacao movimentacao){
        this(
                movimentacao.getId(),
                movimentacao.getId_produto(),
                movimentacao.getEstoque(),
                movimentacao.getDestino(),
                movimentacao.getMotivo(),
                movimentacao.getResponsavel(),
                movimentacao.getData(),
                movimentacao.getHora()
        );
    }
}
