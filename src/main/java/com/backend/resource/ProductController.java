package com.backend.resource;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.pojo.ProductDto;
import com.backend.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@GetMapping("/all")
	public ResponseEntity<List<ProductDto>> allImages() { // TODO Auto-generated method stub
		List<ProductDto> products = productService.allProducts();
		return new ResponseEntity<>(products, HttpStatus.OK); 
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getImage(@PathVariable("id") Long id) { // TODO Auto-generated method stub
		ProductDto product = productService.getProduct(id);
		if(product == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<>(product, HttpStatus.OK); 
	}
	
	@PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> saveImage(@ModelAttribute ProductDto productDto , @RequestParam("file")Optional<MultipartFile>  file) { 
		// TODO Auto-generated method stub
		ProductDto product = new ProductDto();
		try {
			if(file.isPresent()) {
				productDto.setImage(file.get().getBytes());
			}
			product = productService.saveProduct(productDto);
			
			System.out.println(product);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(product, HttpStatus.OK); 
	}
}
