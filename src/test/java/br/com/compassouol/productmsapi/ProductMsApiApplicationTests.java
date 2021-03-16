package br.com.compassouol.productmsapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.compassouol.productmsapi.service.ProductService;

@SpringBootTest
class ProductMsApiApplicationTests {

	@Autowired
	ProductService productService;

	@Test
	void contextLoads() {
	}

}
