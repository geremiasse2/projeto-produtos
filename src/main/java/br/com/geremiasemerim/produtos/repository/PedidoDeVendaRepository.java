package br.com.geremiasemerim.produtos.repository;

import br.com.geremiasemerim.produtos.model.PedidoDeVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoDeVendaRepository extends JpaRepository<PedidoDeVenda, UUID>{
}
