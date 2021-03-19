package br.com.compassouol.productmsapi.resource;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.compassouol.productmsapi.dto.request.ProductInputModelRequest;
import br.com.compassouol.productmsapi.dto.response.ProductModelResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Emanuel Gabriel Sousa
 *
 */

<<<<<<< HEAD
public interface ProductResource {

	@ApiOperation(value = "Lista de produtos", notes = "Este recurso lista os products com paginação", response = ProductModelResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Not found - Não encontrado"),
            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
	})
	ResponseEntity<Page<ProductModelResponse>> buscarTodos(@PageableDefault(size = 5) Pageable pageable);
	
	
	@ApiOperation(value = "Criação de um produto", notes = "Este recurso adiciona um product", response = ProductModelResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Product cadastrado"),
            @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)"),
            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Not found - Não encontrado"),
            @ApiResponse(code = 406, message = "Recurso não possui representação que poderia ser aceita pelo consumidor"),
            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
    })
	ResponseEntity<ProductModelResponse> criar(@Valid @RequestBody ProductInputModelRequest request);
	
	
	 @ApiOperation(value = "Atualização de um produto por ID", notes = "Este recurso atualiza um product por ID", response = ProductModelResponse.class)
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "OK"),
	            @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)"),
	            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
	            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
	            @ApiResponse(code = 404, message = "Not found - Product de ID não encontrado"),
	            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
	    })
	ResponseEntity<ProductModelResponse> atualizar(@ApiParam(name = "id", value = "ID do product", required = true, example = "36544a7a-872b-4468-968d-0d2fabd2c6u3") 
	@PathVariable("id") UUID id, @Valid @RequestBody ProductInputModelRequest request);
	 
	 
	 @ApiOperation(value = "Busca de um produto por ID", notes = "Este recurso buscar um product por ID", response = ProductModelResponse.class)
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "OK"),
	            @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)"),
	            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
	            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
	            @ApiResponse(code = 404, message = "Not found - Product de ID não encontrado"),
	            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
	    })
	 ResponseEntity<ProductModelResponse> buscarPorID(@ApiParam(name = "id", value = "ID do product", required = true, example = "36544a7a-872b-4468-968d-0d2fabd2c6u3") @PathVariable("id") UUID id);
	 
	 
	 @ApiOperation(value = "Lista de produtos filtrados", notes = "Este recurso lista os produtos filtrados de acordo com query parameters", response = ProductModelResponse.class)
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "OK"),
	            @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)"),
	            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
	            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
	            @ApiResponse(code = 404, message = "Not found - Não encontrado"),
	            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
	    })
	 ResponseEntity<Page<ProductModelResponse>> buscarPorFiltro(
			 @ApiParam(name = "q", value = "filtrar por name e description", required = false, example = "name, description") @RequestParam(value = "q", required = false) String q,
			 @ApiParam(name = "min_price", value = "menor preço", required = false, example = "10.5") @RequestParam(value = "min_price", required = false) BigDecimal min_price,
			 @ApiParam(name = "max_price", value = "maior preço", required = true, example = "50")	@RequestParam(value = "max_price", required = false) BigDecimal max_price,
				@PageableDefault(size = 5) Pageable pageable);
	 
	 
	 
	 @ApiOperation(value = "Deleção de um produto por ID", notes = "Este recurso remove um product por ID", response = Void.class)
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "OK"),
	            @ApiResponse(code = 204, message = "No Content - sem conteúdo"),
	            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
	            @ApiResponse(code = 403, message = "Forbidden - Você não tem permissão para acessar este recurso"),
	            @ApiResponse(code = 404, message = "Not found - Product de ID não encontrado"),
	            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
	    })
	 ResponseEntity<Void> remover(@ApiParam(name = "id", value = "ID do product", required = true, example = "36544a7a-872b-4468-968d-0d2fabd2c6u3") @PathVariable("id") UUID id);
=======
@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<Page<ProductModelResponse>> buscarTodos(@PageableDefault(page = 0, size = 5, direction = Direction.ASC) Pageable pageable) {
		LOGGER.info("Requisição para buscar todos os products");
		Page<ProductModelResponse> productsResponse = this.productService.buscarTodos(pageable);
		return ResponseEntity.ok(productsResponse);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProductModelResponse> criar(@Valid @RequestBody ProductInputModelRequest request) {
		LOGGER.info("Requisição recebida para criar um product {}", request);
		ProductModelResponse productModelResponse = this.productService.criar(request);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(productModelResponse.getId()).toUri();
		return ResponseEntity.created(location).body(productModelResponse);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductModelResponse> atualizar(@PathVariable("id") UUID id,
			@Valid @RequestBody ProductInputModelRequest request) {

		LOGGER.info("Requisição recebida para atualizar um product {}", request);
		Optional<ProductModelResponse> productOptional = this.productService.atualizar(id, request);
		if (!productOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(productOptional.get());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductModelResponse> buscarPorID(@PathVariable("id") UUID id) {
		LOGGER.info("Requisição recebida para buscar um product por ID {}", id);
		Optional<ProductModelResponse> clienteResponse = this.productService.buscarPorID(id);
		return clienteResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

	}

	@GetMapping(value = "/search")
	public ResponseEntity<Page<ProductModelResponse>> buscarPorFiltro(
			@RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "min_price", required = false) BigDecimal min_price,
			@RequestParam(value = "max_price", required = false) BigDecimal max_price,
			@PageableDefault(size = 5) Pageable pageable) {

		LOGGER.info("Requisição recebida para buscar product por filtro");
		Page<ProductModelResponse> pageProductResponse = this.productService.buscarProductPorFiltro(q, min_price,
				max_price, pageable);
		return ResponseEntity.ok(pageProductResponse);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable("id") UUID id) {
		LOGGER.info("Requisição recebida para remoção do product por ID {}", id);

		if (this.productService.remover(id)) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
>>>>>>> developer

}
