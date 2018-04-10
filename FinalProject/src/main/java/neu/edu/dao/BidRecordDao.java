package neu.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import neu.edu.entity.BidRecord;
import neu.edu.entity.Startup;
import neu.edu.entity.StartupService;

@Repository
public interface BidRecordDao extends JpaRepository<BidRecord, Integer>{
	
	
	//List<User> findByPersonPersonId(Integer personPersonId);
	public BidRecord findByBidId(Integer bidId);
	public List<BidRecord> findByBidStatusAndStartupService(String bidStatus,StartupService service);
	public List<BidRecord> findByStartupService(StartupService service);
	public List<BidRecord> findByBidStatusAndStartup(String bidStatus,Startup startup);
}

