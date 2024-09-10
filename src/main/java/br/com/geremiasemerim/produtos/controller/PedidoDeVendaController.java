package br.com.geremiasemerim.produtos.controller;

import br.com.geremiasemerim.produtos.model.PedidoDeVenda;
import br.com.geremiasemerim.produtos.repository.PedidoDeVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}   