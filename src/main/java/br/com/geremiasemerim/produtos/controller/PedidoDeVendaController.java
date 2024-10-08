package br.com.geremiasemerim.produtos.controller;

import br.com.geremiasemerim.produtos.model.Cliente;
import br.com.geremiasemerim.produtos.model.PedidoDeVenda;
import br.com.geremiasemerim.produtos.model.Produto;
import br.com.geremiasemerim.produtos.model.dto.ItemQuantidade;
import br.com.geremiasemerim.produtos.model.dto.PedidoDeVendaCreate;
import br.com.geremiasemerim.produtos.model.dto.PedidoDeVendaUpdate;
import br.com.geremiasemerim.produtos.repository.ClienteRepository;
import br.com.geremiasemerim.produtos.repository.PedidoDeVendaRepository;
import br.com.geremiasemerim.produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<PedidoDeVenda>> getTodosPedidosDeVenda(){
        List<PedidoDeVenda> pedidos = pedidoDeVendaRepository.findAll();
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    public ResponseEntity<PedidoDeVenda> criarPedido(@RequestBody PedidoDeVendaCreate pedido){
        Optional<Cliente> possivelCliente = clienteRepository.findById(pedido.cliente());
        if (possivelCliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<Produto> possiveisProdutos = produtoRepository.findAllById(pedido.produtos());
        if (possiveisProdutos.size() != pedido.produtos().size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Double valorTotal = 0.0;
        int quantidadeTotal = 0;

        for (Produto produto : possiveisProdutos) {
            valorTotal += produto.getPreco();
            quantidadeTotal += produto.getQuantidade();
        }
        var totalItens = 0.0;
        for (ItemQuantidade itemQuantidade : pedido.teste()) {
            System.out.printf("Item: %d, Quantidade: %d\n", itemQuantidade.item(), itemQuantidade.quantidade());
            System.out.println("Item: " + itemQuantidade.item() + " Quantidade: " + itemQuantidade.quantidade());
//            totalItens = itemQuantidade.item() * itemQuantidade.quantidade();
//            System.out.println(totalItens);
        }


        PedidoDeVenda pedidoDeVenda = new PedidoDeVenda(
                UUID.randomUUID(),
                pedido.dataVenda(),
                possivelCliente.get(),
                possiveisProdutos,
                quantidadeTotal,
                false,
                pedido.tipoPagamento(),
                valorTotal
        );

        PedidoDeVenda pedidoSalvo = pedidoDeVendaRepository.save(pedidoDeVenda);
        return ResponseEntity.ok(pedidoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDeVenda> atualizarPedido(
        @PathVariable UUID id,
        @RequestBody PedidoDeVendaUpdate pedidoAtualizado) {

       Optional<PedidoDeVenda> possivelPedido = pedidoDeVendaRepository.findById(id);
       
       if (possivelPedido.isEmpty()) {
        return ResponseEntity.notFound().build();
       }

       PedidoDeVenda pedidoAntigo = possivelPedido.get();
       pedidoAntigo.setPago(pedidoAtualizado.pago());
       pedidoAntigo.setProduto(pedidoAtualizado.produto());
       pedidoAntigo.setQuantidade(pedidoAtualizado.quantidade());
       pedidoAntigo.setTipoPagamento(pedidoAtualizado.tipoPagamento());
       
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