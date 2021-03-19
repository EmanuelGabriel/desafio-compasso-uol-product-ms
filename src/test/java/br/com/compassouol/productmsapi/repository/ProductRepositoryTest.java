package br.com.compassouol.productmsapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	private TestEntityManager entityManager;

	@Autowired
	private ProductRepository productRepository;
	

	@Test
	@DisplayName("Deve retornar verdadeiro ao encontrar name e description")
	public void deveBuscarPorNameAndDescription() {

		// Cenário
		String name = "Nome";
		String description = "descrição";
		Pageable pageable = Pageable.unpaged();
		Page<Product> nameDescription = this.productRepository.findByNameAndDescription(name, description, pageable);

		assertThat(nameDescription.getContent()).isEmpty();

	}

	@Test
	@DisplayName("Teste de criação de um novo Product")
	public void criarProductSucesso() {

		Product product = criarProduct();

		Product resultProduct = this.entityManager.persist(product);

		assertThat(this.productRepository.findById(resultProduct.getId()).get()).isEqualTo(product);
		assertThat(resultProduct).isNotNull();
		assertThat(resultProduct.getName()).isNotNull();
		assertThat(resultProduct.getName()).isNotEmpty();
		assertThat(resultProduct.getName()).isEqualTo("Pincel");
		assertThat(resultProduct.getDescription()).isNotNull();
		assertThat(resultProduct.getDescription()).isNotEmpty();
		assertThat(resultProduct.getPrice()).isNotNull();

	}
	

	private Product criarProduct() {
		return new Product("Pincel", "Marcador de quadro", BigDecimal.valueOf(6.70));
	}
}
