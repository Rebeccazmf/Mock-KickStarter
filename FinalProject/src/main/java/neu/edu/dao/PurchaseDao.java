package neu.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import neu.edu.entity.IdeaOption;
import neu.edu.entity.Purchase;

@Repository
public interface PurchaseDao extends JpaRepository<Purchase, Integer>{
	
	
	//List<User> findByPersonPersonId(Integer personPersonId);
	public Purchase findByPurchaseId(Integer purchaseId);
	public List<Purchase> findByPurchaseStatus(String purchaseStatus);
	public List<Purchase> findByIdeaOption(IdeaOption ideaOption);
}
