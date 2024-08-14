package br.com.geremiasemerim.produtos.controller;

import br.com.geremiasemerim.produtos.model.Cliente;
import br.com.geremiasemerim.produtos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<Cliente>> getTodosClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(
            @PathVariable Long id,
            @RequestBody Cliente clienteAtualizado) {

        Optional<Cliente> possivelCliente = clienteRepository.findById(id);

        if (possivelCliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cliente clienteAntigo = possivelCliente.get();
        clienteAntigo.setNome(clienteAtualizado.getNome());
        clienteAntigo.setTelefone(clienteAtualizado.getTelefone());
        clienteAntigo.setEndereco(clienteAtualizado.getEndereco());

        Cliente clienteSalvo = clienteRepository.save(clienteAntigo);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("clientes/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
