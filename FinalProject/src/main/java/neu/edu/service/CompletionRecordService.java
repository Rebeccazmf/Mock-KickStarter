package neu.edu.service;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.controller.completionrecord.CompletionRecordModel;
import neu.edu.dao.CompletionRecordDao;
import neu.edu.dao.IdeaDao;
import neu.edu.dao.ServiceDao;
import neu.edu.dao.UserDao;
import neu.edu.entity.StartupService;
import neu.edu.entity.CompletionRecord;
import neu.edu.entity.Idea;
import neu.edu.entity.User;

@Service
public class CompletionRecordService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private IdeaDao ideaDao;
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private CompletionRecordDao recordDao;
	
	@Transactional
	public boolean createRecord(/*Integer userId,*/ CompletionRecordModel completionRecordModel) {

		boolean created = false;
		//User user = userDao.getOne(userId);
		StartupService ss = serviceDao.getOne(completionRecordModel.getServiceId());

//		if (user == null || (!user.getRole().getRoleName().equals("Startup"))) {
//			return created;
//		}
		CompletionRecord record = new CompletionRecord();
		record.setPercentage(completionRecordModel.getPercentage());
		record.setUpdateDate(completionRecordModel.getUpdateDate());
		record.setStartupService(ss);
	
		record = recordDao.save(record);
		created = true;
		System.out.println("ID Created " + record.getRecordId());
		return created;
	}
	
	@Transactional
	public List<CompletionRecordModel> findByService(Integer serviceId, CompletionRecordModel completionRecordModel){		
		// purchaseStatus = "done";
		StartupService ss = serviceDao.getOne(serviceId);
		return recordDao.findByStartupService(ss).stream().map(x -> {
			CompletionRecordModel temp = new CompletionRecordModel(x.getRecordId());
			temp.setPercentage(x.getPercentage());
			temp.setUpdateDate(x.getUpdateDate());
			temp.setServiceId(x.getStartupService().getServiceId());
			return temp;
		}).collect(Collectors.toList());
	}
	public List<CompletionRecordModel> findAll(){		
		// purchaseStatus = "done";
		return recordDao.findAll().stream().map(x -> {
			CompletionRecordModel temp = new CompletionRecordModel(x.getRecordId());
			temp.setPercentage(x.getPercentage());
			temp.setUpdateDate(x.getUpdateDate());
			temp.setServiceId(x.getStartupService().getServiceId());
			return temp;
		}).collect(Collectors.toList());
	}
//	@Transactional
//	public List<CompletionRecordModel> findByStartup(Integer userId,CompletionRecordModel completionRecordModel){
//		User user = userDao.getOne(userId);
//		List<Idea> ideas = ideaDao.findByUser(user);
//		StartupService ss = serviceDao.getOne(completionRecordModel.getServiceId());
//
//		// purchaseStatus = "done";
//		StartupService ss = serviceDao.getOne(completionRecordModel.getServiceId());
//		return recordDao.findByStartupService(ss).stream().map(x -> {
//			CompletionRecordModel temp = new CompletionRecordModel(x.getRecordId());
//			temp.setPercentage(x.getPercentage());
//			temp.setUpdateDate(x.getUpdateDate());
//			temp.setServiceId(x.getStartupService().getServiceId());
//			return temp;
//		}).collect(Collectors.toList());
//	}
}
