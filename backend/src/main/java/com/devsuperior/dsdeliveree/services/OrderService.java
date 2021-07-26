package com.devsuperior.dsdeliveree.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliveree.dto.OrderDTO;
import com.devsuperior.dsdeliveree.dto.ProductDTO;
import com.devsuperior.dsdeliveree.entities.Order;
import com.devsuperior.dsdeliveree.entities.OrderStatus;
import com.devsuperior.dsdeliveree.entities.Product;
import com.devsuperior.dsdeliveree.repositories.OrderRepository;
import com.devsuperior.dsdeliveree.repositories.ProductRepository;


@Service //Com esta anotação aqu a nossa classe vai ser um componente registado que vai poder ser injectado em outros componentes 
public class OrderService { //Isto vai ser um componente que nós vamos poder injectar lá no controllador

	@Autowired //Ja faz a injeccão de dependencia automaticamente 
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	//este metodo vai ter que retornar uma lista com todos os produtops
	//DTO será Data transfer Object -> é um objecto que carrega os dados que eu mandar;
	//Na parte do repository, vc vai buscar e carregar as entidades, ou seja, carregar objectos do tipo Product.
	
	//Vou ter que transformar uma lista de product para uma lista de ProductDTO, para isso vou usar uma função lambda
	
	@Transactional(readOnly = true) //Isto é uma transação só de leitura para evitar o lock no banco de dados
	public List<OrderDTO> findAll(){
		List<Order>list = repository.findOrdersWithProducts(); //Aqui vai retornar uma lista de Order
		return list.stream().map(x-> new OrderDTO(x)).collect(Collectors.toList());
		
		
	}
	
	@Transactional // neste caso é sem o readOnly porque já vou alterar o meu banco de dados
	public OrderDTO insert(OrderDTO dto){//eSTE É UM METODO PARA INSERIR UM NOVO PEDIDIO JÁ ASSOCIADO COM OS Produtos DELE
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(),
				Instant.now(), OrderStatus.PENDING);
		//Agora vou fazer um for para percorrer todos os ProductDTO dentro do meu dto.getProducts
		for(ProductDTO p: dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
			
		}
		//Este metodo save ele retorna uma referencia para o objecto salvo
		order= repository.save(order);
		//Agora vou retornar esse objecto "order" convertido para OrderDTO passando como argumento.
		return new OrderDTO(order);
	}
	
	/*o METODO insert nao vai retornar uma lista mas apenas um objecto "OrderDTO" correspondente ao pedido que eu inserir no banco  
	  esse metodo vai receber como argumento um OrderDTO dto que será o objecto que eu vou mandar salvar no banco */
	
	
	/*Muito importante: Como eu vou fazer para pegar um OrderDTO dto que vai chegar na requesição, vai ser enviado pelo utilizador lá na aplicação(frontend),
	 * como eu pego esse objecto e salvo no banco ? Para salvar no banco eu vou precisar instanciar um Order apartir de um OrderDTO.
	 
	  Como estou inserindo um objecto, o ID ainda não existe, o instant do pedido não vai ser enviado pelo utilizador, eu posso instanciar aqui um objecto de Instante
	   com um instante atual, um pedido que acabou de ser inserido , ele ainda não está entregue, ou seja, está pendente.
	   
	   Acabamos assim de instanciar um novo objecto do tipo Order com os valores.
	   
	   Só que antes de salvar no banco nós vamos ter que associar este pedido com os produtos que vieram no OrderDTO, então vou fazer um 
	   
	    "for"(ESTRUTURA DE REPETIÇÃO) para percorrer todos os productoDTO dentro do meu dto.getProducts()
	    
	    E agora como eu faço para associar cada produto desse ProductDTO p com o meu Pedido(Order order)?

 Repara que eu tenho entidade que é o Order order e no "For" tenho ProductDTO e não posso misturar DTO com entidade
  Eu vou ter que instanciar uma entidade correspondente a cada ProdutoDTO e para fazer isso  
  vou precisar de uma injeccao de dependencia de ProductRepository como dependencia desse serviço 
  
  
  O que o getOne(id) faz? ele vai instanciar um produto só que ele não vai no banco de dados, ele simplesmente vai criar uma entidade
    gerida pelo JPA PARA QUE EU QUANDO SALVE O MEU PEDIDO(Order order) ele tambem salve as associacoes de quais os produtos tambem estão nesse pedido.  */
	
	
	
	/*O repository ele retorna entidade, só depois é que vou converter para OrderDTO, vou criar um constructor lá que vai converter cada "Order para OrderDTO"  */

	/*RESUMINDO: public List<OrderDTO> insert(OrderDTO dto){...
	 
	  Esse pedido OrderDTO que chegou aqui que é objecto DTO vai conter todos os dados do pedido e tambem os produtos desse pedido,
	  Então o que eu faço? Vou instanciar aqui um pedido que é a minha entidade, vou instanciar aqui um novo com new e depois vou fazer o seguinte, vou percorrer todos os produtos 
	  que estão no meu ProductDTO, VOU CHAMAR CADA UM DELES DE "P", e ai vou fazer o seguinte, 
	  vou instanciar um produto com base no ID */

	
	
	
	@Transactional //Porque vai ser uma alteração no banco de dados
	/*Agora é assim, como vc faz para alterar um registo?
	  Vc vai ter que dar um getOne para instanciar um objecto monitorado pelo JPA
       
      repository é o nosso OrderRepository repository que está no inicio como injeccao de dependencia 
      Instanciei na memoria um pedido sem tocar no banco de dados, o getOne não vai no bd mas está instanciado aqui o nosso pedido
      que é um objecto que vai estar monitorado pelo JPA, ai eu posso fazer alterações nele e depois eu mando salvar, ai ele sim, salva e vai no bando de dados.
      Agora eu vou fazer esse pedido RECEBER status "DELIVERED"
      Feito isto eu vou salvar em que vai ser order= repository.save(order) e ai salvando ele, o order, retornou a referencia para a variavel order
      Por fim vou retornar um novo OrderDTO passando o order como argumento;*/
	public OrderDTO setDelivered(Long id){
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = repository.save(order);
		return new OrderDTO(order);
	}
	
}





