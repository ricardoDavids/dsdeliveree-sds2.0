package com.devsuperior.dsdeliveree.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsdeliveree.dto.OrderDTO;
import com.devsuperior.dsdeliveree.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	
	@GetMapping //Aqui vai ser um endPoint
	public ResponseEntity<List<OrderDTO>>findAll(){
		List<OrderDTO>list = service.findAll(); //Retornou para mim aqui aquela lista de objectos, agora a seguir vou retornar um objecto do tipo RESPONSEENTITY
		return ResponseEntity.ok().body(list); // Agora vou retornar dentro do meu body aquela lista de produtos
}
}
