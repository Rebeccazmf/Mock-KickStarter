package neu.edu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import neu.edu.controller.idea.IdeaModel;
import neu.edu.controller.ideaoption.IdeaOptionModel;
import neu.edu.controller.purchase.PurchaseModel;
import neu.edu.controller.service.ServiceModel;
import neu.edu.dao.IdeaDao;
import neu.edu.dao.IdeaOptionDao;
import neu.edu.dao.PurchaseDao;
import neu.edu.dao.UserDao;
import neu.edu.entity.Category;
import neu.edu.entity.Idea;
import neu.edu.entity.IdeaOption;
import neu.edu.entity.Purchase;
import neu.edu.entity.StartupService;
import neu.edu.entity.User;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IdeaOptionDao optionDao;
	
	@Autowired
	private PurchaseDao purchaseDao;
	
	@Autowired
	private IdeaDao ideaDao;
	

	@Transactional
	public boolean createPurchase(Integer userId, PurchaseModel purchaseModel) {

		boolean created = false;
		User user = userDao.getOne(userId);
		
		IdeaOption ideaOption = optionDao.getOne(purchaseModel.getIdeaOptionId());
		Idea idea = ideaOption.getIdea();
		Integer boughtAmount = ideaOption.getBoughtAmount()+purchaseModel.getPurchaseAmount();
		if(boughtAmount * ideaOption.getOptionPrice()  > ideaOption.getMaxFunding()) {
			return created;
		}
//		if (user == null || (!user.getRole().getRoleName().equals("Guest"))) {
//			return created;
//		}
		Purchase purchase = new Purchase();
		purchase.setPurchaseAmount(purchaseModel.getPurchaseAmount());
		purchase.setPurchaseStatus("Order Placed");
		purchase.setPurchaseDate(new Date());
		purchase.setIdeaOption(ideaOption);
		purchase.setUser(user);
		purchase = purchaseDao.save(purchase);
		
		ideaOption.setBoughtAmount(boughtAmount);
		optionDao.save(ideaOption);
		float gatheredAmount = idea.getGatheredAmount()+purchaseModel.getPurchaseAmount()*ideaOption.getOptionPrice();

		idea.setGatheredAmount(gatheredAmount);
		ideaDao.save(idea);
		created = true;
		System.out.println("ID Created " + purchase.getPurchaseId());
		return created;
	}
	
	@Transactional
	public boolean deletedPurchase(Integer purchaseId) {
		purchaseDao.delete(purchaseId);
		return true;
	}
	
	@Transactional
	public List<PurchaseModel> findAll() {
		// TODO Auto-generated method stub
		return purchaseDao.findAll().stream().map(x -> {
			PurchaseModel temp = new PurchaseModel(x.getPurchaseId());
			temp.setPurchaseStatus(x.getPurchaseStatus());
			temp.setPurchaseDate(x.getPurchaseDate());
			temp.setPurchaseAmount(x.getPurchaseAmount());
			temp.setIdeaOptionId(x.getIdeaOption().getOptionId());
			temp.setUserId(x.getUser().getUserId());
			return temp;
		}).collect(Collectors.toList());
	}
	
	@Transactional
	public boolean updatePurchaseStatus(Integer purchaseId, PurchaseModel purchaseModel) {
		Purchase purchase = purchaseDao.findOne(purchaseId);	
		if(purchase != null){
			purchase.setPurchaseStatus(purchaseModel.getPurchaseStatus());
			purchaseDao.save(purchase);
			return true;
		}else{
			return false;
		}
	}
	
	@Transactional
	public boolean updateGatheredAmount(Integer userId, PurchaseModel purchaseModel) {
		User user = userDao.getOne(userId);
		float amount = purchaseModel.getPurchaseAmount();
		IdeaOption ideaOption = optionDao.getOne(purchaseModel.getIdeaOptionId());
		if (user == null || (!user.getRole().getRoleName().equals("Funder"))) {
			return false;
		}
		Idea toBeUpdated = ideaDao.findOne(ideaOption.getIdea().getIdeaId());
		float gatheredAmount = toBeUpdated.getGatheredAmount() + amount;
		toBeUpdated.setGatheredAmount(gatheredAmount);
		ideaDao.save(toBeUpdated);
		return true;
	}
	//Funder view List of all funding’s done.
	@Transactional
	public List<PurchaseModel> findByStatus(String purchaseStatus){		
		// purchaseStatus = "done";
		return purchaseDao.findByPurchaseStatus(purchaseStatus).stream().map(x -> {
			PurchaseModel temp = new PurchaseModel(x.getPurchaseId());
			temp.setPurchaseAmount(x.getPurchaseAmount());
			temp.setPurchaseDate(x.getPurchaseDate());
			temp.setPurchaseStatus(x.getPurchaseStatus());
			temp.setUserId(x.getUser().getUserId());
			temp.setIdeaOptionId(x.getIdeaOption().getOptionId());
			return temp;
		}).collect(Collectors.toList());
	}
	
	//Admin view Details of Ideas (Funding List)
	@Transactional
	public List<PurchaseModel> findByIdea(Integer ideaId, PurchaseModel purchaseModel){		
//		User admin = userDao.getOne(adminId);
//		if(admin == null || (!admin.getRole().getRoleName().equals("Admin"))) {
//			System.out.println("You do not have permission!");
//			return null;
//		}
		Idea idea = ideaDao.getOne(ideaId);
		List<IdeaOption> ideaOption = optionDao.findByIdea(idea);
		//ideaOption.getIdea()
		List<PurchaseModel> resultList = new ArrayList<PurchaseModel>();
		//ArrayList<PurchaseModel> list = new ArrayList<PurchaseModel>();
		for(IdeaOption io : ideaOption){
			resultList.addAll(purchaseDao.findByIdeaOption(io).stream().map(x -> {
				PurchaseModel temp = new PurchaseModel(x.getPurchaseId());
				temp.setPurchaseAmount(x.getPurchaseAmount());
				temp.setPurchaseDate(x.getPurchaseDate());
				temp.setPurchaseStatus(x.getPurchaseStatus());
				temp.setUserId(x.getUser().getUserId());
				temp.setIdeaOptionId(x.getIdeaOption().getOptionId());
				return temp;
			}).collect(Collectors.toList()));
		}
		return resultList;
	}
	
}
