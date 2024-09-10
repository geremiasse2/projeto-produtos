package br.com.geremiasemerim.produtos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "pedido_de_venda")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDeVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime dataVenda;

    @ManyToOne
    @JoinColumn(name = "clientes_id")
    private Cliente cliente;

    @ManyToMany
    private List<Produto> produto;
    private int quantidade;
    private boolean pago;

    @Enumerated(value = EnumType.STRING)
    private TipoPagamento tipoPagamento;
    private Double valor;

}
