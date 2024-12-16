package com.srv.springwebfluxmongo.utils;

import org.springframework.beans.BeanUtils;

import com.srv.springwebfluxmongo.dto.ProductDto;
import com.srv.springwebfluxmongo.entity.Product;

public class AppUtils {
	public static ProductDto entityToDto(Product product) {
		ProductDto productDto2=new ProductDto();
		BeanUtils.copyProperties(product, productDto2);
		return productDto2;
		
	}
	
	public static Product dtoToEntity(ProductDto productDto2) {
		Product product=new Product();
		BeanUtils.copyProperties(productDto2, product);
		return product;
		
	}

}
