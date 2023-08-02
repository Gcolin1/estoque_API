package com.estoque.estoque.Movimentacao;

import com.estoque.estoque.Produto.dadosCadastroProdutosDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "movimentacao")
@Entity(name = "movimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String id_produto;
    private Integer estoque;
    private String destino;
    private String motivo;
    private String responsavel;
    private String data;
    private String hora;

    public Movimentacao(dadosCadastroMovimentacaoDTO dados, String id_produto){
        this.id_produto = id_produto;
        this.estoque = dados.estoque();
        this.destino = dados.destino();
        this.motivo = dados.motivo();
        this.responsavel = dados.responsavel();
        this.data = dados.data();
        this.hora = dados.hora();
    }
}