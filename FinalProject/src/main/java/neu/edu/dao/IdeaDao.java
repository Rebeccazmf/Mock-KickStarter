package neu.edu.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import neu.edu.entity.Category;
import neu.edu.entity.Idea;
import neu.edu.entity.User;

@Repository
public interface IdeaDao extends JpaRepository<Idea, Integer>{
	
	
	//List<User> findByPersonPersonId(Integer personPersonId);
	public Idea findByIdeaId(Integer ideaId);
	public List<Idea> findByCategory(Category category);
	public List<Idea> findByCategoryOrderByStartDate(Category category);
	public List<Idea> findByUser(User user);
	public Idea findByIdeaName(String ideaName);
	
}