package br.com.compassouol.productmsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compassouol.productmsapi.model.Product;

/**
 * 
 * @author Emanuel Gabriel Sousa
 *
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
