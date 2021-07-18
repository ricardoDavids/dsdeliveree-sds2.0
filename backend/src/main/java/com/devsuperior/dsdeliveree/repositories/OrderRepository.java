package com.devsuperior.dsdeliveree.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsdeliveree.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

	
	/*Para vc implementar o objecto repository que é o objecto que faz o acesso ao Banco de Dados,
	  quando vc usa um framework bom como por exemplo o Spring, isto já vem implementado.

       Então na verdade vamos a proveitar JPA repositopry.
       
       Então o JPA repository é uma interface que já vem no Spring, na verdade ela faz parte do subframwork chamado
       
        SpringDataJPA e o que ela faz? já traz algumas implementações padrão para vc buscar, salvar, deletar e atualizar dados conforme a entidade que vc utilizar.
        
          E qual a entidade? ai vc vai definir no tipo parametrizado<Product, Long> e ai vou colocar o tipo de chave primaria do meu Product que vai 
          ser Long   */
	
	
	/*E ai pessoal basta fazer isso pessoal, que o seu objecto do tipo ProductRepository ele vai 
	  ter todas as operações básicas para vc acessar ao banco de dados relacionados com a sua entidade PRODUCT.
	  
	   O JPA vai tambem permitir fazer consultas ao banco de dados personalizadas de uma forma muito facil. */
	
}
