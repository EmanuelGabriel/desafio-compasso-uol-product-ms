package br.com.compassouol.productmsapi.service.mapper;

import org.springframework.stereotype.Component;

import br.com.compassouol.productmsapi.dto.response.ProductModelResponse;
import br.com.compassouol.productmsapi.model.Product;

@Component
public class ProductResponseMapper implements Mapper<Product, ProductModelResponse> {

	@Override
	public ProductModelResponse map(Product input) {

		if (input == null) {
			return null; // pode ser tratado com uma exceção
		}

		ProductModelResponse response = new ProductModelResponse();
		response.setId(input.getId());
		response.setName(input.getName());
		response.setDescription(input.getDescription());
		response.setPrice(input.getPrice());

		return response;
	}

}
