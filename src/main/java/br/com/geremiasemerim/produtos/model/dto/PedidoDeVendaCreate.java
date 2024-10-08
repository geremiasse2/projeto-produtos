package br.com.geremiasemerim.produtos.model.dto;

import br.com.geremiasemerim.produtos.model.TipoPagamento;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDeVendaCreate(
        LocalDateTime dataVenda,
        Long cliente,
        List<Long> produtos,
        List<ItemQuantidade> teste,
        TipoPagamento tipoPagamento
        ) {
}
