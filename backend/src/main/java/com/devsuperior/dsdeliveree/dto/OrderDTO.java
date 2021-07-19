package com.devsuperior.dsdeliveree.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.dsdeliveree.entities.Order;
import com.devsuperior.dsdeliveree.entities.OrderStatus;

public class OrderDTO implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	/*AGORA VOU FAZER COM QUE CADA PEDIDO JÁ TRAGA COM ELE TAMBEM OS PRODUTOS. 
	 
	  O orderDTO vai carregar além dos dados básicos aqui do pedido tambem uma lista de ProductDTO 
	  */
	
	private Long id;
	private String address;
	private Double latitude;
	private Double longitude;
	private Instant moment;
	private OrderStatus status;
	
	private List<ProductDTO> products = new ArrayList<>();
	
	public OrderDTO() {
		
	}

	public OrderDTO(Long id, String address, Double latitude, Double longitude, Instant moment, OrderStatus status) {
		
		this.id = id;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.moment = moment;
		this.status = status;
	}
public OrderDTO(Order entity) { //Este nosso constructor transforma a nossa entidade Order em um OrderDTO
		
		id =entity.getId();
		address =entity.getAddress();
		latitude =entity.getLatitude();
		longitude =entity.getLongitude();
		moment =entity.getMoment();
		status =entity.getStatus();
		
		/* Agora vou fazer aqui tambem um preenchimento dessa lista de produtos, vou copiar todos os produtos que vierem 
		 
		 na lista de produtos da entidade "Order" para dentro da lista de ProductDTO  */ 
		
		//O que quer dizer a função lambda -> para cada produto da minha lista original eu vou transformar ele em new ProductDTO passando ele, o produto como argumento
		products = entity.getProducts().stream().
				map(x -> new ProductDTO(x)).collect(Collectors.toList());
		
		
	}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public Double getLatitude() {
	return latitude;
}

public void setLatitude(Double latitude) {
	this.latitude = latitude;
}

public Double getLongitude() {
	return longitude;
}

public void setLongitude(Double longitude) {
	this.longitude = longitude;
}

public Instant getMoment() {
	return moment;
}

public void setMoment(Instant moment) {
	this.moment = moment;
}

public OrderStatus getStatus() {
	return status;
}

public void setStatus(OrderStatus status) {
	this.status = status;
}

public List<ProductDTO> getProducts() {
	return products;
}


	
	}
