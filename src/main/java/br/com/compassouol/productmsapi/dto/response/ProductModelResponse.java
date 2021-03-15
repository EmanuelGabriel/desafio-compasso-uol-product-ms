package br.com.compassouol.productmsapi.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

import io.swagger.annotations.ApiModelProperty;

public class ProductModelResponse {

	@ApiModelProperty(value = "ID do product", example = "10c8bfd4-50ee-485b-8975-99327fd4dba8")
	private UUID id;
	
	@ApiModelProperty(value = "Name do product", example = "Ex.: Monitor")
	private String name;
	
	@ApiModelProperty(value = "Description do product", example = "monitor vostro")
	private String description;
	
	@ApiModelProperty(value = "Price do product", example = "10.5")
	private BigDecimal price;

	public ProductModelResponse() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
