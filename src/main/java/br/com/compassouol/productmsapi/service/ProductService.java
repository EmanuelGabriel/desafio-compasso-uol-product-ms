package br.com.compassouol.productmsapi.service;

import java.util.Optional;
import java.util.UUID;

import br.com.compassouol.productmsapi.dto.request.ProductInputModelRequest;
import br.com.compassouol.productmsapi.dto.response.ProductModelResponse;

public interface ProductService {

	ProductModelResponse criar(ProductInputModelRequest request);

	Optional<ProductModelResponse> atualizar(UUID id, ProductInputModelRequest request);

}
