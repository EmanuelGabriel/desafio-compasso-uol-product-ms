package br.com.compassouol.productmsapi.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class ProductInputModelRequest {

	@ApiModelProperty(value = "Name do product", required = true)
	@NotBlank(message = "Campo name não pode ser vazio")
	@Size(max = 50, message = "Campo name deve conter no máximo {max} caracteres")
	private String name;

	@ApiModelProperty(value = "Description do product", required = false)
	@Size(max = 50, message = "Campo description deve conter no máximo {max} caracteres")
	private String description;

	@ApiModelProperty(value = "Price do product", required = true)
	@PositiveOrZero(message = "Campo price deve ser um valor positivo")
	@NotNull(message = "Campo price não pode ser nulo")
	private BigDecimal price;

	public ProductInputModelRequest() {
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
