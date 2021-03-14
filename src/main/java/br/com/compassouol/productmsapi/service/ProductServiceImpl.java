package br.com.compassouol.productmsapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ProductModelResponse criar(ProductInputModelRequest request) {
		LOGGER.info("Criando um registro de product");
		Assert.notNull(request, "Request inválida");
		Product product = productRequestMapper.map(request);
		return productRepository.saveAndFlush(product).map((Product input) -> this.productResponseMapper.map(input));
	}

}
