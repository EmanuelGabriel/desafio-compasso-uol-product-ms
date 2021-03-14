package br.com.compassouol.productmsapi.service.mapper;

import org.springframework.stereotype.Component;

import br.com.compassouol.productmsapi.dto.request.ProductInputModelRequest;
import br.com.compassouol.productmsapi.model.Product;
import br.com.compassouol.productmsapi.service.exception.ProductNotFoundException;

@Component
public class ProductRequestMapper implements Mapper<ProductInputModelRequest, Product> {

	@Override
	public Product map(ProductInputModelRequest input) {

		if (input == null) {
			throw new ProductNotFoundException("Produto não encontrado"); // pode ser tratado com uma exceção
		}

		Product product = new Product();
		product.setName(input.getName());
		product.setDescription(input.getDescription());
		product.setPrice(input.getPrice());

		return product;
	}

}
