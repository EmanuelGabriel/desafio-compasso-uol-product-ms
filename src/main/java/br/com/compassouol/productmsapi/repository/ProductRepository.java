package br.com.compassouol.productmsapi.repository;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.compassouol.productmsapi.model.Product;

/**
 * 
 * @author Emanuel Gabriel Sousa
 *
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

	@Query("SELECT p FROM Product p WHERE p.price BETWEEN :min_price AND :max_price OR p.name LIKE %:q% OR p.description LIKE %:q% ORDER BY p.price")
	Page<Product> buscarPor(@Param("q") String q, @Param("min_price") BigDecimal min_price,
			@Param("max_price") BigDecimal max_price, Pageable pageable);

}
