package br.com.compassouol.productmsapi.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.compassouol.productmsapi.dto.request.ProductInputModelRequest;
import br.com.compassouol.productmsapi.dto.response.ProductModelResponse;
import br.com.compassouol.productmsapi.model.Product;
import br.com.compassouol.productmsapi.repository.ProductRepository;
import br.com.compassouol.productmsapi.service.mapper.Mapper;

/**
 * 
 * @author Emanuel Gabriel Sousa
 *
 */

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private Mapper<ProductInputModelRequest, Product> productRequestMapper;

	@Autowired
	private Mapper<Product, ProductModelResponse> productResponseMapper;

	@Override
	public Page<ProductModelResponse> buscarTodos(Pageable pageable) {
		LOGGER.info("Buscar todos os products");
		Assert.notNull(pageable, "Página inválida");
		return this.productRepository.findAll(pageable).map(product -> this.productResponseMapper.map(product));
	}

	@Override
	public ProductModelResponse criar(ProductInputModelRequest request) {
		LOGGER.info("Criando um product");
		Assert.notNull(request, "Request inválida");
		Product product = this.productRequestMapper.map(request);
		return productRepository.saveAndFlush(product).map((Product input) -> this.productResponseMapper.map(input));
	}

	@Override
	public Optional<ProductModelResponse> atualizar(UUID id, ProductInputModelRequest request) {
		LOGGER.info("Atualizando um product");
		Assert.notNull(id, "ID inválido");

		Product productUpdate = this.productRequestMapper.map(request);
		return productRepository.findById(id).map(prod -> {
			prod.setName(productUpdate.getName());
			prod.setDescription(productUpdate.getDescription());
			prod.setPrice(productUpdate.getPrice());
			return this.productResponseMapper.map(this.productRepository.saveAndFlush(prod));
		});
	}

	@Override
	public Optional<ProductModelResponse> buscarPorID(UUID id) {
		LOGGER.info("Buscar product por ID");
		Assert.notNull(id, "ID inválido");
		return this.productRepository.findById(id).map(this.productResponseMapper::map);
	}

	@Override
	public Page<ProductModelResponse> buscarProductPorFiltro(String q, BigDecimal min_price, BigDecimal max_price,
			Pageable pageable) {
		LOGGER.info("Buscar product por name, description, min_price e max_price");

		return this.productRepository.buscarPor(q, min_price, max_price, pageable)
				.map(prod -> this.productResponseMapper.map(prod));
	}

	@Override
	public boolean remover(UUID id) {

		LOGGER.info("Remove registro do product por ID");
		Assert.notNull(id, "ID inválido");

		try {

			this.productRepository.deleteById(id);
			return true;

		} catch (Exception e) {
			LOGGER.warn("Erro ao remover o registro do product de ID {}", id);
		}

		return false;
	}

}
