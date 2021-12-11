package com.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	private Long id;
	
	private String name;
	
	private Double price;
	
	private Integer stock;
	
	private byte[] image;
}
