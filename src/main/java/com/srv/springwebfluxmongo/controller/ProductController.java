package com.srv.springwebfluxmongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srv.springwebfluxmongo.dto.ProductDto;
import com.srv.springwebfluxmongo.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping
	Flux<ProductDto> getProducts(){
		return productService.getProducts();
		
	}
	@GetMapping("/{id}")
	Mono<ProductDto> getProduct(@PathVariable String id){
		return productService.getProduct(id);
		
	}
	
	@GetMapping("/product-range")
	Flux<ProductDto> getProductBetweenRange(@RequestParam("min") double min,@RequestParam("max") double max){
		return productService.getProductsInRange(min, max);
		
	}
	
	@PostMapping
	Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDto){
		return productService.saveProduct(productDto);
		
	}
	
	@PutMapping("/{id}")
	Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDto,@PathVariable String id){
		return productService.updateProduct(productDto, id);
		
	}
	
	@DeleteMapping("/{id}")
	Mono<Void> deleteProduct(@PathVariable String id){
		return productService.deleteProduct(id);
		
	}

}
