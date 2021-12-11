package com.backend.service;

import java.util.List;

import com.backend.pojo.ProductDto;

public interface ProductService {

	ProductDto saveProduct(ProductDto productDto);
	
	ProductDto getProduct(Long id);
	
	List<ProductDto> allProducts();
}
