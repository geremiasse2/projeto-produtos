package br.com.geremiasemerim.produtos;

import br.com.geremiasemerim.produtos.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProdutosApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;
	public static void main(String[] args) {
		SpringApplication.run(ProdutosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Cliente c1 = new Cliente(null, "Geremias", "48999676802", "Rua Henrique Mezzari, 647");
		clientRepository.;
	}
}
