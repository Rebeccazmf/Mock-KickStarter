package neu.edu.controller.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import neu.edu.controller.ideaoption.IdeaOptionModel;
import neu.edu.controller.role.RoleModel;
import neu.edu.entity.User;
import neu.edu.service.UserService;

//@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@Valid @RequestBody UserModel userModel) {

		ResponseEntity<?> responseEntity = new ResponseEntity<>("User Creation Failed",
				HttpStatus.UNPROCESSABLE_ENTITY);
		;
		UserModel userProfile = null;
		if ((userProfile = userService.createUser(userModel)) != null) {
			responseEntity = new ResponseEntity<>(userProfile, HttpStatus.OK);
		}
		return responseEntity;
	}
	@RequestMapping(method = RequestMethod.POST)
	//@PreAuthorize("hasAuthority('Creator')")
	public UserModel findUserByUsername(@Valid @RequestBody UserModel userModel) {
		return userService.findUserByUsername(userModel.getUsername());
	}
	@RequestMapping(path = "/disable/{userId}",method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('Admin')")
	public UserModel disableUser(@PathVariable("userId") Integer userId) {
		return userService.disableUser(userId);
	}
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> validateUser(@Valid @RequestBody UserModel userModel) {

		ResponseEntity<?> responseEntity = new ResponseEntity<>("User validation error",
				HttpStatus.UNPROCESSABLE_ENTITY);
		;		
		//Integer userId = userService.validateUser(userModel);
		if (userService.validateUser(userModel)) {
			responseEntity = new ResponseEntity<>("User is valid", HttpStatus.OK);
		}
		return responseEntity;
	}
	@RequestMapping(path = "/creator",method = RequestMethod.GET)
	public List<UserModel> findByCreator() {
		Integer roleId = 2;
		return userService.findByCreator(roleId);
	}
	@RequestMapping(path = "/search/creator/{yourName}",method = RequestMethod.GET)
	public List<UserModel> findCreator(@PathVariable("yourName") String yourName) {
		Integer roleId = 2;
		return userService.findUserByNameAndRole(roleId, yourName);
	}
	@RequestMapping(path = "/search/startup/{yourName}",method = RequestMethod.GET)
	public List<UserModel> findStartup(@PathVariable("yourName") String yourName) {
		Integer roleId = 3;
		return userService.findUserByNameAndRole(roleId, yourName);
	}
}
