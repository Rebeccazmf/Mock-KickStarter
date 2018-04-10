package neu.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import neu.edu.entity.Idea;
import neu.edu.entity.IdeaOption;

@Repository
public interface IdeaOptionDao extends JpaRepository<IdeaOption, Integer>{
	
	
	//List<User> findByPersonPersonId(Integer personPersonId);
	public IdeaOption findByOptionId(Integer OptionId);
	public List<IdeaOption> findByIdea(Idea idea);

}
