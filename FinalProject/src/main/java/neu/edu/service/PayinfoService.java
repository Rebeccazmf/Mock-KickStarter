package neu.edu.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import neu.edu.controller.payinfo.PayinfoModel;
import neu.edu.dao.PayinfoDao;
import neu.edu.dao.UserDao;
import neu.edu.entity.CompletionRecord;
import neu.edu.entity.Payinfo;
import neu.edu.entity.User;

@Service
public class PayinfoService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private PayinfoDao payinfoDao;
	
	@Transactional
	public boolean createPayinfo(Integer userId, PayinfoModel payinfoModel) {

		boolean created = false;
		User user = userDao.getOne(userId);
		
//		if (user == null || (!user.getRole().getRoleName().equals("Guest"))) {
//			return created;
//		}
		//...need modify database for hash
		Payinfo payinfo = new Payinfo();
		payinfo.setCardNo(getHashedPassword(payinfoModel.getCardNo()));
		payinfo.setCvv(getHashedPassword(payinfoModel.getCvv()));
		payinfo.setExpDate(getHashedPassword(payinfoModel.getExpDate()));
		payinfo.setUser(user);
		payinfo = payinfoDao.save(payinfo);
		created = true;
		System.out.println("ID Created " + payinfo.getPayinfoId());
		return created;
	}
	
	private  String getHashedPassword(String password) {
	  	ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
	  	String hashed = encoder.encodePassword(password, null);
	  	return hashed;
}

}
