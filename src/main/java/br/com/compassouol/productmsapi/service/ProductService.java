package br.com.compassouol.productmsapi.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.compassouol.productmsapi.dto.request.ProductInputModelRequest;
import br.com.compassouol.productmsapi.dto.response.ProductModelResponse;
import br.com.compassouol.productmsapi.model.Product;

public interface ProductService {

	ProductModelResponse criar(ProductInputModelRequest request);

	Optional<ProductModelResponse> atualizar(UUID id, ProductInputModelRequest request);

	Optional<ProductModelResponse> buscarPorID(UUID id);

	Page<ProductModelResponse> buscarTodos(Pageable pageable);

	Page<ProductModelResponse> buscarProductPorFiltro(String name, BigDecimal min_price, BigDecimal max_price,
			Pageable pageable);

	boolean remover(UUID id);

	Page<Product> findByGlobalPaginado(String busca, int numeroPagina, String sortCampo, String sorDir);

	Iterable<Product> findByProductGlobal(String busca);

}
