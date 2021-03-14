package br.com.compassouol.productmsapi.service;

import br.com.compassouol.productmsapi.dto.request.ProductInputModelRequest;
import br.com.compassouol.productmsapi.dto.response.ProductModelResponse;

public interface ProductService {

	ProductModelResponse criar(ProductInputModelRequest request);

}
