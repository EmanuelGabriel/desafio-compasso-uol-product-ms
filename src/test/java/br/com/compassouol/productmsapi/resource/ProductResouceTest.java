package br.com.compassouol.productmsapi.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.compassouol.productmsapi.dto.request.ProductInputModelRequest;


//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = ProductResource.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Teste de ProductResource")
public class ProductResouceTest {

	private static final String RESOURCE_PATH = "/products";

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Deve exibir todos os products cadastrados")
	public void deveExibirTodosProducts() throws Exception {

		mockMvc.perform(get(RESOURCE_PATH)).andExpect(status().isOk());

	}
	
	@Test
	@DisplayName("Deve criar um novo product")
	public void deveCriarNovoProduct() throws Exception {
		
		ProductInputModelRequest product = new ProductInputModelRequest("Caneta azul", "descrição da caneta azul", BigDecimal.valueOf(6.70));
        
		mockMvc.perform(post(RESOURCE_PATH)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isCreated());
        
	}
	

	@Test
	@DisplayName("Deve lançar erro de validação quando não houver dados suficientes para criação de um product")
	public void criarProductInvalidoTest() {
		// cenário
		// execução
		// verificações
	}
	
	

}
