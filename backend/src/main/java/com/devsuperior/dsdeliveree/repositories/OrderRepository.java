package com.devsuperior.dsdeliveree.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
	
	
	
	/*Agora vou ter que criar aqui um metodo para buscar os pedidos somente quando estado for 0,ou seja, Pending e tem que estar ordenado por moment, que é do mais antigo para o mais recente.
	 * 
	 *  Então vou criar um metodo para retornar uma lista de order. Vou ter que escrever a consulta(query) */
	
	@Query("SELECT DISTINCT obj FROM Order obj JOIN FETCH obj.products "
			+ " WHERE obj.status = 0 ORDER BY obj.moment ASC") /*Esta consulta não vai estar na linguagem de SQL mas sim de JPQL. Para fazer uma busca aqui,  já vai buscar os pedidos 
	            com os seus produtos correspondentes mas terei que fazer um join FETCH(este fetch faz o innerJoin) ele toca o banco de dados 
	            uma vez e trás ja todo o mundo junto com os registos correspondentes.

                 O obj.products é o atributo que representa associação que tirei da classe Order na coleção de prodiutos que se chama products
                 
                 where obj.status tem que ser =0 que é o pendente e vou tambem ordenar do mais antido para o mais recente.*/
	List<Order> findOrdersWithProducts();
	
	
}
