package neu.edu.service;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.controller.Startup.StartupModel;
import neu.edu.controller.bidrecord.BidRecordModel;
import neu.edu.controller.idea.IdeaModel;
import neu.edu.controller.purchase.PurchaseModel;
import neu.edu.controller.role.RoleModel;
import neu.edu.controller.service.ServiceModel;
import neu.edu.controller.user.UserModel;
import neu.edu.dao.BidRecordDao;
import neu.edu.dao.IdeaDao;
import neu.edu.dao.ServiceDao;
import neu.edu.dao.StartupDao;
import neu.edu.dao.UserDao;
import neu.edu.entity.BidRecord;
import neu.edu.entity.Category;
import neu.edu.entity.Idea;
import neu.edu.entity.Role;
import neu.edu.entity.StartupService;
import neu.edu.entity.Startup;
import neu.edu.entity.User;

@Service
public class BidRecordService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private IdeaDao ideaDao;
	@Autowired
	private BidRecordDao bidRecordDao;
	
	@Autowired
	private ServiceDao serviceDao;
	
	@Autowired
	private  StartupDao startupDao;
	
	
	@Transactional
	public boolean createBid(/*Integer userId,*/BidRecordModel bidModel) {
		boolean created = false;
		//User user = userDao.getOne(userId);
		StartupService startupService  = serviceDao.findOne(bidModel.getServiceId());
		Startup startup = startupDao.findOne(bidModel.getStartupId());
//		if (user == null || (!user.getRole().getRoleName().equals("Startup"))) {
//			return created;
//		}
		if(startupService.getEndDate().before(bidModel.getEndDate())){
			return created;
		}
		if(startupService.getBaseAmount() < bidModel.getBidAmount()){
			return created;
		}
		BidRecord bid = new BidRecord();
		bid.setBidStatus("active");
		bid.setBidDesc(bidModel.getBidDesc());
		bid.setBidAmount(bidModel.getBidAmount());
		bid.setStartDate(bidModel.getStartDate());
		bid.setEndDate(bidModel.getEndDate());
		bid.setStartupService(startupService);
		bid.setStartup(startup);
		bid = bidRecordDao.save(bid);
		created = true;
		System.out.println("ID Created " + bid.getBidId());
		return created;
	}
	
	@Transactional
	public boolean deletedBid(Integer bidId) {
		bidRecordDao.delete(bidId);
		return true;
	}
	@Transactional
	public List<BidRecordModel> findAll() {
		// TODO Auto-generated method stub
		return bidRecordDao.findAll().stream().map(x -> {
			BidRecordModel temp = new BidRecordModel(x.getBidId());
			temp.setBidDesc(x.getBidDesc());
			temp.setBidAmount(x.getBidAmount());
			temp.setStartDate(x.getStartDate());
			temp.setEndDate(x.getEndDate());
			temp.setBidStatus(x.getBidStatus());
			temp.setStartupId(x.getStartup().getStartupId());
			temp.setServiceId(x.getStartupService().getServiceId());
			return temp;
		}).collect(Collectors.toList());
	}
	
	@Transactional
	public boolean updateBidStatus(Integer bidId, BidRecordModel bidModel) {
		BidRecord bid = bidRecordDao.findOne(bidId);	
		StartupService startupService  = serviceDao.findOne(bidModel.getServiceId());
		if(bid != null){
			if(bidModel.getBidStatus().equals("done")) {
				startupService.setServiceStatus("done");
			}
			if(bidModel.getBidStatus().equals("accept")) {
				
				for(BidRecord bidrecord	:bidRecordDao.findByStartupService(startupService)) {
					bidrecord.setBidStatus("decline");
				}
				startupService.setServiceStatus("in progress");
			}
			bid.setBidStatus(bidModel.getBidStatus());		
			bidRecordDao.save(bid);
			return true;
		}else{
			return false;
		}
	}
	@Transactional
	public List<BidRecordModel> findByService(Integer serviceId){
		StartupService service = serviceDao.getOne(serviceId);
		if (service == null) {
			return null;
		}

		return bidRecordDao.findByStartupService(service).stream().map(x -> {
			BidRecordModel temp = new BidRecordModel(x.getBidId());
			temp.setBidAmount(x.getBidAmount());
			temp.setBidDesc(x.getBidDesc());
			temp.setBidStatus(x.getBidStatus());
			temp.setStartDate(x.getStartDate());
			temp.setEndDate(x.getEndDate());
			temp.setServiceId(x.getStartupService().getServiceId());
			temp.setStartupId(x.getStartup().getStartupId());
			return temp;
		}).collect(Collectors.toList());
	}
	
	
	//Startup views accepted services from different Ideas.
	@Transactional
	public List<BidRecordModel> findByStatusService(String ideaName){
		Idea idea = ideaDao.findByIdeaName(ideaName);
		List<StartupService> services = serviceDao.findByIdea(idea);
		List<BidRecordModel> resultList = new ArrayList<BidRecordModel>();
		for(StartupService service : services) {
			resultList.addAll(bidRecordDao.findByBidStatusAndStartupService("accept", service).stream().map(x -> {
			BidRecordModel temp = new BidRecordModel(x.getBidId());
			temp.setBidDesc(x.getBidDesc());
			temp.setBidAmount(x.getBidAmount());;
			temp.setBidStatus(x.getBidStatus());
			temp.setStartDate(x.getStartDate());
			temp.setEndDate(x.getEndDate());
			temp.setServiceId(x.getStartupService().getServiceId());
			temp.setStartupId(x.getStartup().getStartupId());
			return temp;
		}).collect(Collectors.toList()));	 
		
		}
		 return resultList;
	}
	
	@Transactional
	public List<BidRecordModel> findByCreator(Integer userId){
		User user = userDao.getOne(userId);
		List<Idea> ideas = ideaDao.findByUser(user);
		List<StartupService> serviceList = new ArrayList<StartupService>();
		for(Idea idea : ideas) {
			serviceList.addAll(serviceDao.findByIdea(idea));
			//System.out.println(serviceDao.findByIdea(idea));
		}
		//System.out.println(serviceList);
		List<BidRecordModel> bidList = new ArrayList<BidRecordModel>();

		for(StartupService service : serviceList) {
		bidList.addAll(bidRecordDao.findByStartupService(service).stream().map(x -> {
			BidRecordModel temp = new BidRecordModel(x.getBidId());
			temp.setBidAmount(x.getBidAmount());
			temp.setBidDesc(x.getBidDesc());
			temp.setBidStatus(x.getBidStatus());
			temp.setStartDate(x.getStartDate());
			temp.setEndDate(x.getEndDate());
			temp.setServiceId(x.getStartupService().getServiceId());
			temp.setStartupId(x.getStartup().getStartupId());
			return temp;
		}).collect(Collectors.toList()));
	}
		return bidList;
	}
	
}
