package neu.edu.service;

import java.util.List;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.controller.category.CategoryModel;
import neu.edu.controller.shoppingcart.ShoppingCartModel;
import neu.edu.dao.IdeaOptionDao;
import neu.edu.dao.ShoppingCartDao;
import neu.edu.dao.UserDao;
import neu.edu.entity.IdeaOption;
import neu.edu.entity.ShoppingCart;
import neu.edu.entity.User;

@Service
public class ShoppingCartService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ShoppingCartDao shoppingCartDao;
	
	@Autowired
	private IdeaOptionDao ideaOptionDao;
	

	@Transactional
	public boolean createCart(Integer userId, ShoppingCartModel cartModel) {
		boolean created = false;
		User user = userDao.getOne(userId);
		IdeaOption ideaOption = ideaOptionDao.getOne(cartModel.getOptionId());
		if (user == null || (!user.getRole().getRoleName().equals("Guest"))) {
			return created;
		}
		ShoppingCart cart = new ShoppingCart();
		cart.setAddedOnDate(cartModel.getAddedOnDate());
		cart.setUser(user);
		cart.setIdeaOption(ideaOption);
		cart = shoppingCartDao.save(cart);
		created = true;
		System.out.println("ID Created " + cart.getShoppingCartId());
		return created;
	}
	
	@Transactional
	public boolean deletedCart(Integer cartId) {
		shoppingCartDao.delete(cartId);
		return true;
	}
	@Transactional
	public List<ShoppingCartModel> findAll() {
		// TODO Auto-generated method stub
		return shoppingCartDao.findAll().stream().map(x -> {
			ShoppingCartModel temp = new ShoppingCartModel(x.getShoppingCartId());
			temp.setAddedOnDate(x.getAddedOnDate());
			temp.setUserId(x.getUser().getUserId());
			temp.setOptionId(x.getIdeaOption().getOptionId());
			return temp;
		}).collect(Collectors.toList());
	}
	
}
