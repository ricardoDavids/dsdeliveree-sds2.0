package com.devsuperior.dsdeliveree.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsdeliveree.dto.ProductDTO;
import com.devsuperior.dsdeliveree.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	
	@GetMapping //Aqui vai ser um endPoint
	public ResponseEntity<List<ProductDTO>>findAll(){
		List<ProductDTO>list = service.findAll(); //Retornou para mim aqui aquela lista de objectos, agora a seguir vou retornar um objecto do tipo RESPONSEENTITY
		return ResponseEntity.ok().body(list); // Agora vou retornar dentro do meu body aquela lista de produtos
}
}
