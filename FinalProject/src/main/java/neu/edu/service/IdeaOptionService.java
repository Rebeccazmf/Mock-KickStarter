package neu.edu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.controller.ideaoption.IdeaOptionModel;
import neu.edu.controller.purchase.PurchaseModel;
import neu.edu.controller.role.RoleModel;
import neu.edu.controller.service.ServiceModel;
import neu.edu.dao.IdeaDao;
import neu.edu.dao.IdeaOptionDao;
import neu.edu.dao.StartupDao;
import neu.edu.dao.UserDao;
import neu.edu.entity.Category;
import neu.edu.entity.Idea;
import neu.edu.entity.IdeaOption;
import neu.edu.entity.Role;
import neu.edu.entity.Startup;
import neu.edu.entity.User;

@Service
public class IdeaOptionService {
	@Autowired
	private IdeaDao ideaDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IdeaOptionDao optionDao;
	
	@Transactional
	public boolean createIdeaOption(/*Integer userId, */IdeaOptionModel optionModel) {

		boolean created = false;
//		User user = userDao.getOne(userId);
		Idea idea = ideaDao.getOne(optionModel.getIdeaId());
//		if (user == null || (!user.getRole().getRoleName().equals("Creator"))) {
//			return created;
//		}
		IdeaOption ideaOption = new IdeaOption();
		ideaOption.setOptionPrice(optionModel.getOptionPrice());
		ideaOption.setOptionDesc(optionModel.getOptionDesc());
		ideaOption.setBoughtAmount(optionModel.getBoughtAmount());
		ideaOption.setMaxFunding(optionModel.getMaxFunding());
		ideaOption.setIdea(idea);		
		ideaOption = optionDao.save(ideaOption);
		created = true;
		System.out.println("ID Created " + ideaOption.getOptionId());
		return created;
	}
	
	@Transactional
	public boolean deletedOption(Integer optionId) {
		optionDao.delete(optionId);
		return true;
	}
	
	@Transactional
	public List<IdeaOptionModel> findAll() {
		// TODO Auto-generated method stub
		return optionDao.findAll().stream().map(x -> {
			IdeaOptionModel temp = new IdeaOptionModel(x.getOptionId());
			temp.setBoughtAmount(x.getBoughtAmount());
			temp.setOptionDesc(x.getOptionDesc());
			temp.setOptionPrice(x.getOptionPrice());
			temp.setMaxFunding(x.getMaxFunding());
			temp.setIdeaId(x.getIdea().getIdeaId());
			return temp;
		}).collect(Collectors.toList());
	}
	
	@Transactional
	public List<IdeaOptionModel> findByIdea(Integer ideaId) {
		// TODO Auto-generated method stub
		Idea idea = ideaDao.getOne(ideaId);

		return optionDao.findByIdea(idea).stream().map(x -> {
			IdeaOptionModel temp = new IdeaOptionModel(x.getOptionId());
			temp.setBoughtAmount(x.getBoughtAmount());
			temp.setOptionDesc(x.getOptionDesc());
			temp.setOptionPrice(x.getOptionPrice());
			temp.setMaxFunding(x.getMaxFunding());
			temp.setIdeaId(x.getIdea().getIdeaId());
			return temp;
		}).collect(Collectors.toList());
	}
	@Transactional
	public List<IdeaOptionModel> findByCreator(Integer userId) {
		User user = userDao.getOne(userId);
		
		// TODO Auto-generated method stub
		List<Idea> ideas = ideaDao.findByUser(user);
		List<IdeaOptionModel> resultList = new ArrayList<IdeaOptionModel>();
		for(Idea idea:ideas)
			resultList.addAll(optionDao.findByIdea(idea).stream().map(x -> {
			IdeaOptionModel temp = new IdeaOptionModel(x.getOptionId());
			temp.setBoughtAmount(x.getBoughtAmount());
			temp.setOptionDesc(x.getOptionDesc());
			temp.setOptionPrice(x.getOptionPrice());
			temp.setMaxFunding(x.getMaxFunding());
			temp.setIdeaId(x.getIdea().getIdeaId());
			return temp;
		}).collect(Collectors.toList()));
		return resultList;
	}

	


}
