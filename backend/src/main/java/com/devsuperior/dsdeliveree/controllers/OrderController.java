package com.devsuperior.dsdeliveree.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO dto){
		dto = service.insert(dto);	
		
		
		//O codigo 201 é quando um recurso é criado, passando inclusive o endereço do recurso criado no cabeçalho da resposta
		/*É uma chamada que vc faz para vc criar uma URI que corresponde ao recurso que vc criou, ai para vc retornar a resposta 201 bonita é assim
		  return ResponseEntity.created(URI=location).body(dto)   */
		URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
		}
	
	
}

