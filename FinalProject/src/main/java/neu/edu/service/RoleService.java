package neu.edu.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.controller.role.RoleModel;
import neu.edu.dao.RoleDao;
import neu.edu.dao.UserDao;
import neu.edu.entity.Role;
import neu.edu.entity.User;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public boolean createRole(/*Integer userId,*/ RoleModel roleModel) {

		boolean created = false;
//		User user = userDao.getOne(userId);
//		if (user == null || (!user.getRole().getRoleName().equals("Admin"))) {
//			return created;
//		}
		Role role = new Role();
		role.setRoleName(roleModel.getRoleName());
		role.setRoleDesc(roleModel.getRoleDesc());
		role = roleDao.save(role);
		created = true;
		System.out.println("ID Created " + role.getRoleId());
		return created;
	}
	
	@Transactional
	public List<RoleModel> findAll() {
		// TODO Auto-generated method stub
		return roleDao.findAll().stream().map(x -> {
			RoleModel temp = new RoleModel(x.getRoleId());
			temp.setRoleName(x.getRoleName());
			temp.setRoleDesc(x.getRoleDesc());
			return temp;
		}).collect(Collectors.toList());
	}

	@Transactional
	public boolean deleteRole(Integer userId, Integer roleId) {
		User user = userDao.getOne(userId);
		if (user == null || (!user.getRole().getRoleName().equals("Admin"))) {
			return false;
		}
		Role toBeDeleted = roleDao.findOne(roleId);
		if (toBeDeleted.getUsers().size() > 0) {
			return false;
		} else {
			roleDao.delete(toBeDeleted);
		}
		return true;
	}

	@Transactional
	public boolean updateRole(Integer userId, Integer roleId, RoleModel newRole) {
		User user = userDao.getOne(userId);
		if (user == null || (!user.getRole().getRoleName().equals("Admin"))) {
			return false;
		}
		Role toBeUpdated = roleDao.findOne(roleId);
		toBeUpdated.setRoleDesc(newRole.getRoleDesc());
		toBeUpdated.setRoleName(newRole.getRoleName());

		roleDao.save(toBeUpdated);
		return true;
	}

}
