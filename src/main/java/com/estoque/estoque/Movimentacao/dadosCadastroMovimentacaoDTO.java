package com.estoque.estoque.Movimentacao;

public record dadosCadastroMovimentacaoDTO(
        Integer estoque,
        String destino,
        String motivo,
        String responsavel,
        String data,
        String hora
) {
}
