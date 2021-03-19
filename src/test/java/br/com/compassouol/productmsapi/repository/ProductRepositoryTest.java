package br.com.compassouol.productmsapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.compassouol.productmsapi.model.Product;

/**
 * 
 * @author Emanuel Gabriel
 * @version 1.0
 *
 */

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("Testes de ProductRepository")
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	@DisplayName("Deve buscar um product por seu nome com sucesso")
	public void deveBuscarProductPorNameSucesso() {

		// Cenário
		String nameProduct = "Motorola G8";

		Page<Product> pageProduct = this.productRepository.buscarPor(nameProduct, null, null, null);

		assertThat(pageProduct.getContent().get(0).getName()).isEqualTo(nameProduct);

	}


	@Test
	@DisplayName("Teste de criação de um novo Product")
	public void criarProductSucesso() {

		Product product = criarNovoProduct();

		Product resultProduct = this.productRepository.save(product);

		assertThat(this.productRepository.findById(resultProduct.getId()).get()).isEqualTo(product);
		assertThat(resultProduct).isNotNull();
		assertThat(resultProduct.getName()).isNotNull();
		assertThat(resultProduct.getName()).isNotEmpty();
		assertThat(resultProduct.getName()).isEqualTo("Pincel");
		assertThat(resultProduct.getDescription()).isNotNull();
		assertThat(resultProduct.getDescription()).isNotEmpty();
		assertThat(resultProduct.getPrice()).isNotNull();

	}

	private Product criarNovoProduct() {
		return new Product("Pincel", "Marcador de quadro", BigDecimal.valueOf(6.70));
	}
}
