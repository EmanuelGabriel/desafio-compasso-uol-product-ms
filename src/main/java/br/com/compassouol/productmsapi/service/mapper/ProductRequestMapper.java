package br.com.compassouol.productmsapi.service.mapper;

import org.springframework.stereotype.Component;

import br.com.compassouol.productmsapi.dto.request.ProductInputModelRequest;
import br.com.compassouol.productmsapi.model.Product;

@Component
public class ProductRequestMapper implements Mapper<ProductInputModelRequest, Product> {

	@Override
	public Product map(ProductInputModelRequest input) {

		if (input == null) {
			return null; // pode ser tratado com uma exceção
		}
		
		/** 
		 	Entrada:
		  {
		    "name": "nome",
		    "description": "descrição",
		    "price": <preco>
		  }
		  
		*/

		Product product = new Product();
		product.setName(input.getName());
		product.setDescription(input.getDescription());
		product.setPrice(input.getPrice());

		return product;
	}

}
