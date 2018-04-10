package neu.edu.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.controller.Startup.StartupModel;
import neu.edu.controller.service.ServiceModel;
import neu.edu.dao.CategoryDao;
import neu.edu.dao.IdeaDao;
import neu.edu.dao.ServiceDao;
import neu.edu.dao.StartupDao;
import neu.edu.dao.UserDao;
import neu.edu.entity.Category;
import neu.edu.entity.Idea;
import neu.edu.entity.Role;
import neu.edu.entity.Startup;
import neu.edu.entity.User;

@Service
public class StartupService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private StartupDao startupDao;
	@Autowired
	private CategoryDao categoryDao;
	
	@Transactional
	public boolean createStartup(Integer userId, StartupModel startupModel) {
		boolean created = false;
		User user = userDao.getOne(userId);
		Category category = categoryDao.getOne(startupModel.getCategoryId());
		if (user == null || (!user.getRole().getRoleName().equals("Guest"))) {
			return created;
		}
		Startup startup = new Startup();
		startup.setUser(user);
		startup.setCategory(category);

		startup = startupDao.save(startup);
		created = true;
		System.out.println("ID Created " + startup.getStartupId());
		return created;
	}
	
	@Transactional
	public List<StartupModel> findAll() {
		// TODO Auto-generated method stub
		return startupDao.findAll().stream().map(x -> {
			StartupModel temp = new StartupModel(x.getStartupId());
			
			temp.setCategoryId(x.getCategory().getCategoryId());
			temp.setUserId(x.getUser().getUserId());
			return temp;
		}).collect(Collectors.toList());
	}
	
	@Transactional
	public boolean deletedStartup(Integer startupId) {
	
	Startup toBeDeleted = startupDao.findOne(startupId);
		if (toBeDeleted.getBidRecords().size() > 0) {
			return false;
		} else {
			startupDao.delete(toBeDeleted);
			return true;
		}
	}

	
	
}
