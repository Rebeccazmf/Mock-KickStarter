package neu.edu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.controller.bidrecord.BidRecordModel;
import neu.edu.controller.idea.IdeaModel;
import neu.edu.controller.user.UserModel;
import neu.edu.dao.BidRecordDao;
import neu.edu.dao.CategoryDao;
import neu.edu.dao.IdeaDao;
import neu.edu.dao.ServiceDao;
import neu.edu.dao.StartupDao;
import neu.edu.dao.UserDao;
import neu.edu.entity.BidRecord;
import neu.edu.entity.Category;
import neu.edu.entity.Idea;
import neu.edu.entity.Startup;
import neu.edu.entity.StartupService;
import neu.edu.entity.User;

@Service
public class IdeaService {
	@Autowired
	private IdeaDao ideaDao;
	@Autowired
	private StartupDao startupDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private BidRecordDao bidRecordDao;
	@Autowired
	private ServiceDao serviceDao;
	
	@Transactional
	public boolean createIdea(Integer userId, IdeaModel ideaModel) {

		boolean created = false;
		User user = userDao.getOne(userId);
		//User user = userDao.getOne(ideaModel.getUserId());
		Category category = categoryDao.getOne(ideaModel.getCategoryId());
//		if (user == null || (!user.getRole().getRoleName().equals("Creator"))) {
//			return created;
//		}
		Idea idea = new Idea();
		idea.setIdeaName(ideaModel.getIdeaName());
		idea.setIdeaDesc(ideaModel.getIdeaDesc());
		//idea.setUser(user);
		idea.setUser(user);
		idea.setStartDate(ideaModel.getStartDate());
		idea.setEndDate(ideaModel.getEndDate());
		idea.setGatheredAmount(0);
		idea.setTargetAmount(ideaModel.getTargetAmount());
		idea.setIdeaStatus("Open");
		
		idea.setCategory(category);
		
		idea = ideaDao.save(idea);
		created = true;
		System.out.println("ID Created " + idea.getIdeaId());
		return created;
	}
	
	@Transactional
	public List<IdeaModel> findAll() {
		// TODO Auto-generated method stub
		return ideaDao.findAll().stream().map(x -> {
			IdeaModel temp = new IdeaModel(x.getIdeaId());
			temp.setIdeaName(x.getIdeaName());
			temp.setIdeaDesc(x.getIdeaDesc());
			temp.setTargetAmount(x.getTargetAmount());
			temp.setGatheredAmount(x.getGatheredAmount());
			temp.setStartDate(x.getStartDate());
			temp.setEndDate(x.getEndDate());
			temp.setIdeaStatus(x.getIdeaStatus());
			temp.setCategoryId(x.getCategory().getCategoryId());
			return temp;
		}).collect(Collectors.toList());
	}
	
	@Transactional
	public List<IdeaModel> findIdeaByUser(Integer userId){
		User user = userDao.getOne(userId);
		if (user == null) {
			return null;
		}

		return ideaDao.findByUser(user).stream().map(x -> {
			IdeaModel temp = new IdeaModel(x.getIdeaId());
			temp.setIdeaName(x.getIdeaName());
			temp.setIdeaDesc(x.getIdeaDesc());
			temp.setTargetAmount(x.getTargetAmount());
			temp.setGatheredAmount(x.getGatheredAmount());
			temp.setStartDate(x.getStartDate());
			temp.setEndDate(x.getEndDate());
			temp.setIdeaStatus(x.getIdeaStatus());
			temp.setCategoryId(x.getCategory().getCategoryId());
			return temp;
		}).collect(Collectors.toList());
	}
	@Transactional
	public List<IdeaModel> findIdeaByCategory(Integer categoryId){
		
		Category category = categoryDao.getOne(categoryId);
		if (category == null) {
			return null;
		}

		return ideaDao.findByCategory(category).stream().map(x -> {
			IdeaModel temp = new IdeaModel(x.getIdeaId());
			temp.setIdeaName(x.getIdeaName());
			temp.setIdeaDesc(x.getIdeaDesc());
			temp.setTargetAmount(x.getTargetAmount());
			temp.setGatheredAmount(x.getGatheredAmount());
			temp.setStartDate(x.getStartDate());
			temp.setEndDate(x.getEndDate());
			temp.setIdeaStatus(x.getIdeaStatus());
			temp.setCategoryId(categoryId);
			return temp;
		}).collect(Collectors.toList());
	}
	@Transactional
	public List<IdeaModel> findByStartupOrderByStartDate(Integer userId){
		User user = userDao.getOne(userId);
		Startup startup = startupDao.findByUser(user);
		Category category = startup.getCategory();
		if (category == null) {
			return null;
		}

		return ideaDao.findByCategoryOrderByStartDate(category).stream().map(x -> {
			IdeaModel temp = new IdeaModel(x.getIdeaId());
			temp.setIdeaName(x.getIdeaName());
			temp.setIdeaDesc(x.getIdeaDesc());
			temp.setTargetAmount(x.getTargetAmount());
			temp.setGatheredAmount(x.getGatheredAmount());
			temp.setStartDate(x.getStartDate());
			temp.setEndDate(x.getEndDate());
			temp.setIdeaStatus(x.getIdeaStatus());
			temp.setCategoryId(x.getCategory().getCategoryId());
			return temp;
		}).collect(Collectors.toList());
	}
	@Transactional
	public List<IdeaModel> findByStartup(Integer userId){		
		User user = userDao.getOne(userId);
//		if(admin == null || (!admin.getRole().getRoleName().equals("Admin"))) {
//			System.out.println("You do not have permission!");
//			return null;
//		}
		Startup startup = startupDao.findByUser(user);
		
		List<BidRecord> bidrecords = bidRecordDao.findByBidStatusAndStartup("accept",startup);
		List<StartupService> serviceList = new ArrayList<StartupService>();
		List<Idea> ideaList = new ArrayList<Idea>();
		List<IdeaModel> resultList = new ArrayList<IdeaModel>();
		for(BidRecord bid : bidrecords){
			serviceList.add(bid.getStartupService());
		}
		for(StartupService service : serviceList){
			ideaList.add(service.getIdea());
		}
		for(Idea idea : ideaList){
			IdeaModel ideaModel = new IdeaModel();
			ideaModel.setIdeaName(idea.getIdeaName());
			ideaModel.setIdeaDesc(idea.getIdeaDesc());
			ideaModel.setTargetAmount(idea.getTargetAmount());
			ideaModel.setGatheredAmount(idea.getGatheredAmount());
			ideaModel.setStartDate(idea.getStartDate());
			ideaModel.setEndDate(idea.getEndDate());
			ideaModel.setIdeaStatus(idea.getIdeaStatus());
			ideaModel.setUserId(idea.getUser().getUserId());
			ideaModel.setCategoryId(idea.getCategory().getCategoryId());
			resultList.add(ideaModel);
		}
		
		return resultList;
	}
	
	@Transactional
	public boolean deleteIdea(Integer userId, Integer ideaId) {
		User user = userDao.getOne(userId);
		if (user == null || (!user.getRole().getRoleName().equals("Admin"))) {
			return false;
		}
		Idea toBeDeleted = ideaDao.findOne(ideaId);
		if (toBeDeleted.getCategory() != null) {
			return false;
		} else {
			ideaDao.delete(toBeDeleted);
		}
		return true;
	}
	
//	@Transactional
//	public boolean updateGatheredAmount(Integer userId, Integer roleId, IdeaModel ideaModel) {
//		User user = userDao.getOne(userId);
//		if (user == null || (!user.getRole().getRoleName().equals("Guest"))) {
//			return false;
//		}
//		Idea toBeUpdated = ideaDao.findOne(roleId);
//		toBeUpdated.setGatheredAmount(ideaModel.getGatheredAmount());
//		ideaDao.save(toBeUpdated);
//		return true;
//	}
	@Transactional
	public boolean updateTargetAmount(Integer userId, Integer roleId, IdeaModel ideaModel) {
		User user = userDao.getOne(userId);
		if (user == null || (!user.getRole().getRoleName().equals("Creator"))) {
			return false;
		}
		Idea toBeUpdated = ideaDao.findOne(roleId);
		toBeUpdated.setTargetAmount(ideaModel.getTargetAmount());
		ideaDao.save(toBeUpdated);
		return true;
	}
	
	@Transactional
	public boolean updateStatus(Integer userId, Integer ideaId, IdeaModel ideaModel) {
		User user = userDao.getOne(userId);
		if (user == null || (!user.getRole().getRoleName().equals("Creator"))) {
			return false;
		}
		Idea toBeUpdated = ideaDao.findOne(ideaId);
		toBeUpdated.setIdeaStatus(ideaModel.getIdeaStatus());
		ideaDao.save(toBeUpdated);
		return true;
	}
	
	@Transactional
	public List<IdeaModel> checkIdeaStatus(Integer userId) {
		User user = userDao.getOne(userId);
		List<Idea> ideas = ideaDao.findByUser(user);
		List<IdeaModel> resultList = new ArrayList<IdeaModel>();
		for(Idea idea : ideas) {
			if(idea.getIdeaStatus().equals("Open")) {
				
					IdeaModel ideaModel = new IdeaModel();
					ideaModel.setIdeaName(idea.getIdeaName());
					ideaModel.setIdeaDesc(idea.getIdeaDesc());
					ideaModel.setTargetAmount(idea.getTargetAmount());
					ideaModel.setGatheredAmount(idea.getGatheredAmount());
					ideaModel.setStartDate(idea.getStartDate());
					ideaModel.setEndDate(idea.getEndDate());
					ideaModel.setIdeaStatus(idea.getIdeaStatus());
					ideaModel.setUserId(idea.getUser().getUserId());
					ideaModel.setCategoryId(idea.getCategory().getCategoryId());
					resultList.add(ideaModel);
					
			}
		}
		return resultList;
	}
}
