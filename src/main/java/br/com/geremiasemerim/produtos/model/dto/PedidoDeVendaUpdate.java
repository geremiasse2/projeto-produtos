package br.com.geremiasemerim.produtos.model.dto;

import br.com.geremiasemerim.produtos.model.Produto;
import br.com.geremiasemerim.produtos.model.TipoPagamento;

import java.util.List;

public record PedidoDeVendaUpdate(
        List<Produto> produto,
        int quantidade,
        boolean pago,
        TipoPagamento tipoPagamento)
 {
}
