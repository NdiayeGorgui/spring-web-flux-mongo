package com.srv.springwebfluxmongo.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProductDto {
	
	private String id;
	private String name;
	private int qty;
	private double price;
	

}
