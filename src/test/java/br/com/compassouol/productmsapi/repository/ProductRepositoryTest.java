package br.com.compassouol.productmsapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.math.BigDecimal;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.compassouol.productmsapi.model.Product;

/**
 * 
 * @author Emanuel Gabriel
 * @version 1.0
 *
 */

@DataJpaTest
@DisplayName("Classe de teste de ProductRepository")
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;
	
	
	@Test
	@DisplayName("Teste de criação de um novo Product")
	public void criarProductSucesso() {
		
		
		Product product = criarProduct();
		
		Product resultProduct = this.productRepository.save(product);
		
		assertThat(resultProduct).isNotNull();
		assertThat(resultProduct.getName()).isNotNull();
		assertThat(resultProduct.getName()).isNotEmpty();
		assertThat(resultProduct.getName()).isEqualTo("Pincel");
		assertThat(resultProduct.getDescription()).isNotNull();		
		assertThat(resultProduct.getDescription()).isNotEmpty();		
		
		
	}
	
	@Test
	@DisplayName("Teste de restrição de um Product com name em branco")
	public void criarProductValidacaoNameBranco() {
		
		
		Product product = criarProduct();
		
		assertThatExceptionOfType(ConstraintViolationException.class)
				.isThrownBy(() -> this.productRepository.save(product))
				.withMessage("Name do product não pode ser vazio");
		
		
		
	}
	
	private Product criarProduct() {
		return new Product("Pincel", "Marcador de quadro", BigDecimal.valueOf(6.70));
	}
}
