package neu.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import neu.edu.entity.ShoppingCart;


@Repository
public interface ShoppingCartDao extends JpaRepository<ShoppingCart, Integer>{
	
	//List<User> findByPersonPersonId(Integer personPersonId);
	public ShoppingCart findByShoppingCartId(Integer shoppingCartId);
}
