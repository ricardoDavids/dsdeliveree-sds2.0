package com.devsuperior.dsdeliveree.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliveree.dto.ProductDTO;
import com.devsuperior.dsdeliveree.entities.Product;
import com.devsuperior.dsdeliveree.repositories.ProductRepository;


@Service //Com esta anotação aqu a nossa classe vai ser um componente registado que vai poder ser injectado em outros componentes 
public class ProductService { //Isto vai ser um componente que nós vamos poder injectar lá no controllador

	@Autowired //Ja faz a injeccão de dependencia automaticamente 
	private ProductRepository repository;
	
	
	//este metodo vai ter que retornar uma lista com todos os produtops
	//DTO será Data transfer Object -> é um objecto que carrega os dados que eu mandar;
	//Na parte do repository, vc vai buscar e carregar as entidades, ou seja, carregar objectos do tipo Product.
	
	//Vou ter que transformar uma lista de product para uma lista de ProductDTO, para isso vou usar uma função lambda
	
	@Transactional(readOnly = true) //Isto é uma transação só de leitura para evitar o lock no banco de dados
	public List<ProductDTO> findAll(){
		List<Product>list = repository.findAllByOrderByNameAsc();
		return list.stream().map(x-> new ProductDTO(x)).collect(Collectors.toList());
		
		
	}
}
