package br.com.compassouol.productmsapi.resource;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	 
	 
	 @ApiOperation(value = "Lista de produtos com filtros", notes = "Este recurso lista os produtos filtrados de acordo com query parameters", response = ProductModelResponse.class)
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "OK"),
	            @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)"),
	            @ApiResponse(code = 401, message = "Unauthorized - não autorizado"),
	            @ApiResponse(code = 403, message = "Forbidden - Cliente não tem permissão para acessar este recurso"),
	            @ApiResponse(code = 404, message = "Not found - Não encontrado"),
	            @ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
	    })
	 ResponseEntity<Page<ProductModelResponse>> buscarPorFiltro(
			 @ApiParam(name = "q", value = "filtrar por name e description", required = false, example = "name, description") @RequestParam(value = "q", required = false) String q,
			 @ApiParam(name = "min_price", value = "menor preço", required = false, example = "10.5") @RequestParam(value = "min_price", required = false) BigDecimal min_price,
			 @ApiParam(name = "max_price", value = "maior preço", required = false, example = "50")	@RequestParam(value = "max_price", required = false) BigDecimal max_price,
				@PageableDefault(page = 0, size = 5) Pageable pageable);
	 
	 
	 
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


	
}
