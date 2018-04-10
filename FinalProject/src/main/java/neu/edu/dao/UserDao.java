package neu.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import neu.edu.entity.Role;
import neu.edu.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	
	
	//List<User> findByPersonPersonId(Integer personPersonId);
	public User findByUsernameAndPassword(String username, String password);
	public User findByUsername(String username);
	public User findByUserId(Integer userId);
	public List<User> findByRole(Role role);
	public List<User> findByYourNameAndRole(String yourname,Role role);
}