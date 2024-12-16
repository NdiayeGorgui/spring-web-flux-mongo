package com.srv.springwebfluxmongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Range;

import com.srv.springwebfluxmongo.dto.ProductDto;
import com.srv.springwebfluxmongo.entity.Product;
import com.srv.springwebfluxmongo.repo.ProductRepository;
import com.srv.springwebfluxmongo.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public Flux<ProductDto>getProducts(){
		return productRepository.findAll().map(AppUtils::entityToDto);
	}
	
	public Mono<ProductDto>getProduct(String id){
		return productRepository.findById(id).map(AppUtils::entityToDto);
	}
	
	public Flux<ProductDto>getProductsInRange(double min,double max){
		return productRepository.findByPriceBetween(Range.closed(min, max));
	}
	
	public Mono<ProductDto>saveProduct(Mono<ProductDto> productDto ){ //onetomany use flatmap
	 return	productDto.map(AppUtils::dtoToEntity)
		.flatMap(productRepository::insert)                         //mono of mono
		.map(AppUtils::entityToDto);
		
	}
	
	public Mono<ProductDto>updateProduct(Mono<ProductDto> productDto,String id ){ //onetomany use flatmap
	  return	productRepository.findById(id)
			.flatMap(p->productDto.map(AppUtils::dtoToEntity)
		 	.doOnNext(e->e.setId(id)))
			.flatMap(productRepository::save)
			.map(AppUtils::entityToDto);
			
		}
	
	public Mono<Void>deleteProduct(String id){
		return productRepository.deleteById(id);
	}

}
