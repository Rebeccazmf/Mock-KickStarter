package neu.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import neu.edu.entity.Idea;
import neu.edu.entity.StartupService;

@Repository
public interface ServiceDao extends JpaRepository<StartupService, Integer> {
	
	public StartupService findByServiceId(Integer serviceId);
	public List<StartupService> findByIdea(Idea idea);
	public List<StartupService> findByIdeaAndServiceStatus(Idea idea, String serviceStatus);
}