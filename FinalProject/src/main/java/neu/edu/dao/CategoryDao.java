package neu.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import neu.edu.entity.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer>{
	
	
	//List<User> findByPersonPersonId(Integer personPersonId);
	public Category findByCategoryId(Integer categoryId);
}
