package neu.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import neu.edu.entity.BidRecord;
import neu.edu.entity.Category;
import neu.edu.entity.Idea;
import neu.edu.entity.Startup;
import neu.edu.entity.User;

@Repository
public interface StartupDao extends JpaRepository<Startup, Integer> {
	
	//public List<Startup> findByStartupName(String startupName);
	public Startup findByUser(User user);
}
