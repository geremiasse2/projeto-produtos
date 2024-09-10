package br.com.geremiasemerim.produtos.model;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDeVendaRecord(
        LocalDateTime dataVenda,
        Long cliente,
        List<Long> produtos,
        int quantidade,
        boolean pago,
        TipoPagamento tipoPagamento,
        Boolean valor
        ) {
}
