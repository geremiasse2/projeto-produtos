package br.com.geremiasemerim.produtos.controller;

import br.com.geremiasemerim.produtos.model.PedidoDeVenda;
import br.com.geremiasemerim.produtos.repository.PedidoDeVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoDeVendaController {

    @Autowired
    private PedidoDeVendaRepository pedidoDeVendaRepository;

    @GetMapping
    public ResponseEntity<List<PedidoDeVenda>> getTodosPedidosDeVenda(){
        List<PedidoDeVenda> pedidos = pedidoDeVendaRepository.findAll();
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    public ResponseEntity<PedidoDeVenda> criarPedido(@RequestBody PedidoDeVenda pedido){
        PedidoDeVenda pedidoSalvo = pedidoDeVendaRepository.save(pedido);
        return ResponseEntity.ok(pedidoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDeVenda> AtualizarPedido(
        @PathVariable UUID id,
        @RequestBody PedidoDeVenda pedidoAtualizado) {

       Optional<PedidoDeVenda> possivelPedido = pedidoDeVendaRepository.findById(id);
       
       if (possivelPedido.isEmpty()) {
        return ResponseEntity.notFound().build();
       }

       PedidoDeVenda pedidoAntigo = possivelPedido.get();
       pedidoAntigo.setPago(pedidoAtualizado.isPago());
       pedidoAntigo.setProduto(pedidoAtualizado.getProduto());
       pedidoAntigo.setQuantidade(pedidoAtualizado.getQuantidade());
       pedidoAntigo.setTipoPagamento(pedidoAtualizado.getTipoPagamento());
       
       PedidoDeVenda pedidoSalvo = pedidoDeVendaRepository.save(pedidoAntigo);
       return ResponseEntity.ok(pedidoSalvo);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable UUID id) {
        if (pedidoDeVendaRepository.existsById(id)) {
            pedidoDeVendaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();

    }


}   