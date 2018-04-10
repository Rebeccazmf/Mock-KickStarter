package neu.edu.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.controller.category.CategoryModel;
import neu.edu.dao.CategoryDao;
import neu.edu.dao.UserDao;
import neu.edu.entity.Category;
import neu.edu.entity.User;

@Service
public class CategoryService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	
	@Transactional
	public boolean createCategory(/*Integer userId, */CategoryModel categoryModel) {
		boolean created = false;
//		User user = userDao.getOne(userId);
//		
//		if (user == null || (!user.getRole().getRoleName().equals("Admin"))) {
//			return created;
//		}
		Category category = new Category();
		category.setCategoryName(categoryModel.getCategoryName());
		category.setCategoryDesc(categoryModel.getCategoryDesc());
		category = categoryDao.save(category);
		created = true;
		System.out.println("ID Created " + category.getCategoryId());
		return created;
	}
	
	@Transactional
	public boolean deletedCategory(Integer categoryId) {
		categoryDao.delete(categoryId);
		return true;
	}

	@Transactional
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll().stream().map(x -> {
			CategoryModel temp = new CategoryModel(x.getCategoryId());
			temp.setCategoryName(x.getCategoryName());
			temp.setCategoryDesc(x.getCategoryDesc());
			return temp;
		}).collect(Collectors.toList());
	}
	
}
