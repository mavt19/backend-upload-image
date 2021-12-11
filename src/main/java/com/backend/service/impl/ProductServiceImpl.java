package com.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.backend.domain.Product;
import com.backend.pojo.ProductDto;
import com.backend.repository.ProductRepository;
import com.backend.service.ProductService;
import com.backend.utils.ImageUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	public ProductDto saveProduct(ProductDto productDto) {
		// TODO Auto-generated method stub
		 byte[] imageFile = null;
		 if(productDto.getImage() != null) {
			 imageFile = ImageUtils.compressBytes(productDto.getImage());
		 }
		Product product = new Product(null, productDto.getName(), productDto.getPrice(), productDto.getStock(),
				imageFile);
		productRepository.save(product);
		return productDto;
	}

	@Override
	public ProductDto getProduct(Long id) {
		// TODO Auto-generated method stub
		ProductDto productDto = null;
		Product product = productRepository.findById(id).orElse(null);
		if(product !=null) {
			productDto = new ProductDto();
			BeanUtils.copyProperties(product, productDto);
			if(productDto.getImage() != null) {
				productDto.setImage(ImageUtils.decompressBytes(productDto.getImage())); 
			}
			
		}
		return productDto;
	}

	@Override
	public List<ProductDto> allProducts() {
		// TODO Auto-generated method stub
		List<Product> products = productRepository.findAll();
		List<ProductDto> productsFinal = new ArrayList<ProductDto>();
		products.forEach(x -> { 
			ProductDto p = new ProductDto();
			p.setId(x.getId());
			p.setName(x.getName());
			p.setPrice(x.getPrice());
			p.setStock(x.getStock());
			p.setImage(x.getImage()!=null? ImageUtils.decompressBytes(x.getImage()):null); 
			productsFinal.add(p);
		});
		return productsFinal;
	}

}
