package neu.edu.service;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.controller.idea.IdeaModel;
import neu.edu.controller.ideaoption.IdeaOptionModel;
import neu.edu.controller.service.ServiceModel;
import neu.edu.dao.IdeaDao;
import neu.edu.dao.ServiceDao;
import neu.edu.dao.StartupDao;
import neu.edu.dao.UserDao;
import neu.edu.entity.Category;
import neu.edu.entity.Idea;
import neu.edu.entity.Startup;
import neu.edu.entity.StartupService;
import neu.edu.entity.User;

@Service
public class ServiceService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private StartupDao startupDao;
	@Autowired
	private IdeaDao ideaDao;
	
	@Transactional
	public boolean createService(/*Integer userId, */ServiceModel serviceModel) {
		boolean created = false;
		//User user = userDao.getOne(userId);
		Idea idea = ideaDao.getOne(serviceModel.getIdeaId());
//		if (user == null || (!user.getRole().getRoleName().equals("Creator"))) {
//			return created;
//		}
		StartupService service = new StartupService();
		service.setServiceStatus("active");
		service.setServiceDesc(serviceModel.getServiceDesc());
		service.setStartDate(serviceModel.getStartDate());
		service.setEndDate(serviceModel.getEndDate());
		service.setBaseAmount(serviceModel.getBaseAmount());
		service.setIdea(idea);
		service = serviceDao.save(service);
		created = true;
		System.out.println("ID Created " + service.getServiceId());
		return created;
	}
	
	@Transactional
	public List<ServiceModel> findAll() {
		// TODO Auto-generated method stub
		return serviceDao.findAll().stream().map(x -> {
			ServiceModel temp = new ServiceModel(x.getServiceId());
			temp.setStartDate(x.getStartDate());
			temp.setEndDate(x.getEndDate());
			temp.setBaseAmount(x.getBaseAmount());
			temp.setServiceDesc(x.getServiceDesc());
			temp.setServiceStatus(x.getServiceStatus());
			temp.setIdeaId(x.getIdea().getIdeaId());
			return temp;
		}).collect(Collectors.toList());
	}
	
	@Transactional
	public boolean deletedService(Integer serviceId) {
		serviceDao.delete(serviceId);
		return true;
	}
	
	@Transactional
	public boolean updateServiceStatus(Integer serviceId, ServiceModel serviceModel) {
		StartupService service = serviceDao.findOne(serviceId);	
		if(service != null){
			service.setServiceStatus(serviceModel.getServiceStatus());		
			serviceDao.save(service);
			return true;
		}else{
			return false;
		}
	}
	
	@Transactional
	public List<ServiceModel> findByIdea(Integer ideaId){
		Idea idea = ideaDao.getOne(ideaId);
		if (idea == null) {
			return null;
		}

		return serviceDao.findByIdea(idea).stream().map(x -> {
			ServiceModel temp = new ServiceModel(x.getServiceId());
			temp.setServiceDesc(x.getServiceDesc());
			temp.setServiceStatus(x.getServiceStatus());
			temp.setStartDate(x.getStartDate());
			temp.setEndDate(x.getEndDate());
			temp.setBaseAmount(x.getBaseAmount());
			temp.setIdeaId(ideaId);
			return temp;
		}).collect(Collectors.toList());
	}
	
	@Transactional
	public List<ServiceModel> findByCreator(Integer userId){
		User user = userDao.getOne(userId);
		List<Idea> ideas = ideaDao.findByUser(user);
		List<ServiceModel> resultList = new ArrayList<ServiceModel>();
		for(Idea idea:ideas)
		resultList.addAll(serviceDao.findByIdea(idea).stream().map(x -> {
			ServiceModel temp = new ServiceModel(x.getServiceId());
			temp.setServiceDesc(x.getServiceDesc());
			temp.setServiceStatus(x.getServiceStatus());
			temp.setStartDate(x.getStartDate());
			temp.setEndDate(x.getEndDate());
			temp.setBaseAmount(x.getBaseAmount());
			temp.setIdeaId(x.getIdea().getIdeaId());
			return temp;
		}).collect(Collectors.toList()));
		return resultList;
	}
				
	@Transactional
	public List<ServiceModel> findByIdeaStatus(Integer ideaId, ServiceModel serviceModel){
		Idea idea = ideaDao.getOne(ideaId);
		if (idea == null) {
			return null;
		}

		return serviceDao.findByIdeaAndServiceStatus(idea, serviceModel.getServiceStatus()).stream().map(x -> {
			ServiceModel temp = new ServiceModel(x.getServiceId());
			temp.setServiceDesc(x.getServiceDesc());
			temp.setServiceStatus(x.getServiceStatus());
			temp.setStartDate(x.getStartDate());
			temp.setEndDate(x.getEndDate());
			temp.setBaseAmount(x.getBaseAmount());
			temp.setIdeaId(ideaId);
			return temp;
		}).collect(Collectors.toList());
	}
	
	public List<ServiceModel> findByStartup(Integer userId) {
		User user = userDao.getOne(userId);
		Startup startup = startupDao.findByUser(user);
		Category category = startup.getCategory();
		List<Idea> ideas = ideaDao.findByCategory(category);
		List<ServiceModel> resultList = new ArrayList<ServiceModel>();
		for(Idea idea:ideas)
			resultList.addAll(serviceDao.findByIdea(idea).stream().map(x -> {
				ServiceModel temp = new ServiceModel(x.getServiceId());
				temp.setServiceDesc(x.getServiceDesc());
				temp.setServiceStatus(x.getServiceStatus());
				temp.setStartDate(x.getStartDate());
				temp.setEndDate(x.getEndDate());
				temp.setBaseAmount(x.getBaseAmount());
				temp.setIdeaId(x.getIdea().getIdeaId());
				return temp;
			}).collect(Collectors.toList()));
			return resultList;
	}
}
