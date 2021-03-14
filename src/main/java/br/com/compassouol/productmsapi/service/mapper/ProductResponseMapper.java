package br.com.compassouol.productmsapi.service.mapper;

import org.springframework.stereotype.Component;

import br.com.compassouol.productmsapi.dto.response.ProductModelResponse;
import br.com.compassouol.productmsapi.model.Product;
import br.com.compassouol.productmsapi.service.exception.ProductNotFoundException;

@Component
public class ProductResponseMapper implements Mapper<Product, ProductModelResponse> {

	@Override
	public ProductModelResponse map(Product input) {

		if (input == null) {
			throw new ProductNotFoundException("Produto não encontrado"); // pode ser tratado com uma exceção
		}

		ProductModelResponse response = new ProductModelResponse();
		response.setId(input.getId());
		response.setName(input.getName());
		response.setDescription(input.getDescription());
		response.setPrice(input.getPrice());

		return response;
	}

}
