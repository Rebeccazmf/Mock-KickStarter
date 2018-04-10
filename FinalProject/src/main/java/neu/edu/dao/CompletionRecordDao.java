package neu.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import neu.edu.entity.CompletionRecord;
import neu.edu.entity.StartupService;

public interface CompletionRecordDao extends JpaRepository<CompletionRecord, Integer>{
	
	//List<User> findByPersonPersonId(Integer personPersonId);
	public CompletionRecord findByRecordId(Integer recordId);
	public List<CompletionRecord> findByStartupService(StartupService service);
}
