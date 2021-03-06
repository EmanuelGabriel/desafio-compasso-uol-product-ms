package br.com.compassouol.productmsapi.resource;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.compassouol.productmsapi.dto.request.ProductInputModelRequest;
import br.com.compassouol.productmsapi.dto.response.ProductModelResponse;
import br.com.compassouol.productmsapi.model.Product;
import br.com.compassouol.productmsapi.service.ProductService;
import io.swagger.annotations.Api;

/**
 * 
 * @author Emanuel Gabriel Sousa
 *
 */

@Api(produces = "application/json", consumes = "application/json", value = "Gerencia products", tags = {
		"Gerencia os products" })
@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductResourceImp implements ProductResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductResourceImp.class);

	@Autowired
	private ProductService productService;

	@Override
	@GetMapping
	public ResponseEntity<Page<ProductModelResponse>> buscarTodos(@PageableDefault(size = 5) Pageable pageable) {
		LOGGER.info("Requisi????o para buscar todos os products");
		Page<ProductModelResponse> productModelResponsePage = this.productService.buscarTodos(pageable);
		return ResponseEntity.ok(productModelResponsePage);

	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProductModelResponse> criar(@Valid @RequestBody ProductInputModelRequest request) {
		LOGGER.info("Requisi????o recebida para criar um product {}", request);
		ProductModelResponse productModelResponse = this.productService.criar(request);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(productModelResponse.getId()).toUri();
		return ResponseEntity.created(location).body(productModelResponse);
	}

	@Override
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductModelResponse> atualizar(@PathVariable("id") UUID id,
			@Valid @RequestBody ProductInputModelRequest request) {

		LOGGER.info("Requisi????o recebida para atualizar um product {}", request);
		Optional<ProductModelResponse> productOptional = this.productService.atualizar(id, request);
		if (!productOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(productOptional.get());
	}

	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductModelResponse> buscarPorID(@PathVariable("id") UUID id) {
		LOGGER.info("Requisi????o recebida para buscar product por ID {}", id);
		Optional<ProductModelResponse> clienteResponse = this.productService.buscarPorID(id);
		return clienteResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

	}

	@Override
	@GetMapping(value = "/search")
	public ResponseEntity<Page<ProductModelResponse>> buscarPorFiltro(String q, BigDecimal min_price,
			BigDecimal max_price, Pageable pageable) {

		LOGGER.info("Requisi????o recebida para buscar product por filtro");
		Page<ProductModelResponse> pageProductResponse = this.productService.buscarProductPorFiltro(q, min_price,
				max_price, pageable);
		return ResponseEntity.ok(pageProductResponse);

	}

	@GetMapping(value = "page/paginado")
	public ResponseEntity<Page<Product>> buscarPorTodosCampos(
			@RequestParam(value = "busca", required = false) String busca,
			@RequestParam(value = "numeroPagina", required = false) int numeroPagina,
			@RequestParam(value = "sortCampo", required = false) String sortCampo,
			@RequestParam(value = "sortDir", required = false) String sortDir) {
		Page<Product> pageProduct = productService.findByGlobalPaginado(busca, numeroPagina, sortCampo, sortDir);
		return ResponseEntity.ok(pageProduct);
	}

	@GetMapping(value = "page")
	public ResponseEntity<Iterable<Product>> buscarPor(@RequestParam(value = "busca", required = false) String busca) {
		var buscaProduct = productService.findByProductGlobal(busca);
		return buscaProduct != null ? ResponseEntity.ok(buscaProduct) : ResponseEntity.ok().build();
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable("id") UUID id) {
		LOGGER.info("Requisi????o recebida para remo????o do product por ID {}", id);

		if (this.productService.remover(id)) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
