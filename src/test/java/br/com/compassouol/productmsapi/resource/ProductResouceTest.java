package br.com.compassouol.productmsapi.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.compassouol.productmsapi.dto.request.ProductInputModelRequest;
import br.com.compassouol.productmsapi.dto.response.ProductModelResponse;
import br.com.compassouol.productmsapi.service.ProductService;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Teste de ProductResource")
public class ProductResouceTest {

	//URL base para acesso desse resource/controller
	private static final String RESOURCE_PATH = "/products";

	//Instância do MockMVC
	@Autowired
	private MockMvc mockMvc;
	
	//Instância do ObjectMapper para trabalhar com JSON
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ProductService productService;
	

	@Test
	@DisplayName("Deve exibir todos os products cadastrados")
	public void deveExibirTodosProducts() throws Exception {
		mockMvc.perform(get(RESOURCE_PATH))
			.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Deve criar um novo product com sucesso")
	public void deveCriarNovoProduct() throws Exception {
		
		ProductInputModelRequest product = novoProduct();
		String json = this.objectMapper.writeValueAsString(product);
		
		mockMvc.perform(post(RESOURCE_PATH)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(json))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("id").isNotEmpty())
				.andExpect(jsonPath("name").isNotEmpty())
				.andExpect(jsonPath("price").isNotEmpty());
		
	}

	
	@Test
	@DisplayName("Deve obter um product por seu ID com sucesso")
	public void deveBuscarProductPorUUIDSucesso() throws Exception {
		
		// Cenário 
		
		Optional<ProductModelResponse> uuidProduct = this.productService.buscarPorID(UUID.fromString("36544a7a-872b-4468-968d-0d2fabd2c6b6"));
		
		// Execução
		mockMvc
			.perform(get(RESOURCE_PATH.concat("/" + uuidProduct.get().getId()))
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.accept(MediaType.APPLICATION_JSON_VALUE))
			      .andExpect(status().isOk());
		
	}
	
	@Test
	@DisplayName("Deve retornar not found ID do product não encontrado")
	public void deveRetornarNotFound404ProductId() throws Exception {
	 
		// cenário
		 
	}
	
	
	private ProductInputModelRequest novoProduct() {
		return new ProductInputModelRequest("Caneta azul", "descrição da caneta azul", BigDecimal.valueOf(6.70));
	}

}
