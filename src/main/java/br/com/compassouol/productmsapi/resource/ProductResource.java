package br.com.compassouol.productmsapi.resource;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.compassouol.productmsapi.dto.request.ProductInputModelRequest;
import br.com.compassouol.productmsapi.dto.response.ProductModelResponse;
import br.com.compassouol.productmsapi.service.ProductService;

/**
 * 
 * @author Emanuel Gabriel Sousa
 *
 */

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);

	@Autowired
	private ProductService productService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProductModelResponse> criar(@Valid @RequestBody ProductInputModelRequest request) {
		LOGGER.info("Requisição recebida para criar um registro {}", request);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(request.getName())
				.toUri();
		return ResponseEntity.created(location).body(this.productService.criar(request));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductModelResponse> atualizar(@PathVariable("id") UUID id,
			@Valid @RequestBody ProductInputModelRequest request) {

		LOGGER.info("Requisição recebida para atualizar registro {}", request);
		Optional<ProductModelResponse> clienteOptional = this.productService.atualizar(id, request);
		if (!clienteOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clienteOptional.get());
	}

}
