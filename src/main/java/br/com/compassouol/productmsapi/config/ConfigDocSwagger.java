package br.com.compassouol.productmsapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ConfigDocSwagger {

	@Bean
	public Docket docAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.compassouol.productmsapi.resource"))
                .paths(PathSelectors.ant("/products/**"))
                .build()
                .apiInfo(apiInfo());
	}
	
	 private ApiInfo apiInfo(){
	        return new ApiInfoBuilder()
	                .title("Catálogo de produtos - API")
	                .description("Gerencia o catálogo de dados de produtos")
	                .version("1.0.0")
	                .license("Compasso UOL - A cada desafio, uma nova solução.")
	                .contact(new Contact("Emanuel Gabriel Sousa", "https://www.sgstech.com.br", "emanuel.gabriel.sousa@protonmail.com"))
	                .build();
	    }
	
}

