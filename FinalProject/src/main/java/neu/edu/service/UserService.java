package neu.edu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import neu.edu.controller.ideaoption.IdeaOptionModel;
import neu.edu.controller.purchase.PurchaseModel;
import neu.edu.controller.role.RoleModel;
import neu.edu.controller.user.UserModel;
import neu.edu.dao.CategoryDao;
import neu.edu.dao.IdeaDao;
import neu.edu.dao.RoleDao;
import neu.edu.dao.StartupDao;
import neu.edu.dao.UserDao;
import neu.edu.entity.Category;
import neu.edu.entity.Idea;
import neu.edu.entity.Purchase;
import neu.edu.entity.Role;
import neu.edu.entity.Startup;
import neu.edu.entity.User;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private IdeaDao ideaDao;
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private StartupDao startupDao;
	
	@Transactional
	public UserModel createUser(UserModel userModel) {

		Role role = roleDao.findOne(userModel.getRoleId());
		UserModel userProfile = null;

		if (role != null) {
			
			User user = new User();
			user.setUsername(userModel.getUsername());
			user.setPassword(getHashedPassword(userModel.getPassword()));
			user.setYourName(userModel.getYourName());
			user.setUserDesc(userModel.getUserDesc());
			user.setRole(role);
			user.setUserStatus(userModel.getUserStatus());
			user = userDao.save(user);

			userProfile = new UserModel();
			userProfile.setUserId(user.getUserId());
			userProfile.setRoleId(userModel.getRoleId());
			userProfile.setUsername(user.getUsername());
			
			if(role.getRoleName().equals("Startup")) {
				Startup startup = new Startup();
				Category category = categoryDao.getOne(userModel.getCategoryId());
				startup.setCategory(category);
				startup.setUser(user);
				startup = startupDao.save(startup);
			}
		} else {
			return userProfile;
		}
	
		return userProfile;

	}
	@Transactional
	public boolean deletedUser(Integer userId) {
		userDao.delete(userId);
		return true;
	}
	
	@Transactional
	public boolean updateUserStatus(Integer userId, UserModel userModel) {
		User user = userDao.findOne(userId);	
		if(user != null){
			user.setUserStatus(userModel.getUserStatus());
			userDao.save(user);
			return true;
		}else{
			return false;
		}
	}
	@Transactional
	public UserModel disableUser(Integer userId) {
		User user = userDao.findOne(userId);
		List<Idea> ideas = ideaDao.findByUser(user);
		UserModel userProfile = new UserModel();
		if(user != null){
			user.setUserStatus("disable");
			userDao.save(user);
			for(Idea idea: ideas) {
				idea.setIdeaStatus("disable");
				ideaDao.save(idea);
			}
			userProfile.setUserId(user.getUserId());
			userProfile.setRoleId(user.getRole().getRoleId());
			userProfile.setUsername(user.getUsername());
			userProfile.setUserStatus(user.getUserStatus());
			userProfile.setYourName(user.getYourName());
			userProfile.setUserDesc(user.getUserDesc());
			
		}
		return userProfile;
	}
	@Transactional
	public boolean checkIdea(Integer userId) {
		User user = userDao.findOne(userId);	
		if(user.getIdeas().size() > 0){
			return true;
		}else{
			return false;
		}
	}
	@Transactional
	public UserModel findUserByUsername(String username) {
		User user = userDao.findByUsername(username);
		//return String.valueOf(user.getUserId());
		UserModel userProfile = new UserModel();
		userProfile.setUserId(user.getUserId());
		userProfile.setRoleId(user.getRole().getRoleId());
		userProfile.setUsername(username);
		return userProfile;
	}
	@Transactional
	public List<UserModel> findUserByNameAndRole(Integer roleId, String yourname) {
		Role role = roleDao.findOne(roleId);
		return userDao.findByYourNameAndRole(yourname,role).stream().map(x -> {
			UserModel temp = new UserModel(x.getUserId());
			temp.setYourName(x.getYourName());
			temp.setUserDesc(x.getUserDesc());
			temp.setUsername(x.getUsername());
			temp.setRoleId(x.getRole().getRoleId());
			temp.setUserStatus(x.getUserStatus());
			return temp;
		}).collect(Collectors.toList());		
	}
	@Transactional
	public boolean validateUser(UserModel userModel){
		String username = userModel.getUsername();
		String password = userModel.getPassword();
		password = getHashedPassword(password);
		User userValid = userDao.findByUsernameAndPassword(username, password);
		if(userValid != null && userValid.getUserStatus().equals("active")){
			System.out.println("user is valid" + userValid.getUsername());
			return true;
		}
		return false;
	}
	private  String getHashedPassword(String password) {
	  	ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
	  	String hashed = encoder.encodePassword(password, null);
	  	return hashed;
}
	@Transactional
	public List<UserModel> findByCreator(Integer roleId) {
		// TODO Auto-generated method stub
		Role role = roleDao.getOne(roleId);
		
		return userDao.findByRole(role).stream().map(x -> {
			UserModel temp = new UserModel(x.getUserId());
			temp.setYourName(x.getYourName());
			temp.setUserDesc(x.getUserDesc());
			temp.setUsername(x.getUsername());
			temp.setRoleId(roleId);
			temp.setUserStatus(x.getUserStatus());
			return temp;
		}).collect(Collectors.toList());
	}
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 User user = userDao.findByUsername(username);
		 
		  if(user == null) {
	            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", username));
	        }
		  
		  System.out.println(" User Role -->"+user.getRole().getRoleName());
		  List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
		
	    UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), authorities);

		return userDetails;
	}
	
/*	@Override

	@Transactional

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	// TODO Auto-generated method stub

	User user = userDao.findByUsername(username);


	 if(user == null) {

	 throw new UsernameNotFoundException(String.format("The username %s doesn't exist", username));

	 }

	 List<GrantedAuthority> grantedAuthorities= user.getAuthorities();

	 grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));

	 return user;

	}*/
}
