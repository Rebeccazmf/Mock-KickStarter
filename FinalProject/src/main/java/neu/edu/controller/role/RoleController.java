package neu.edu.controller.role;

import java.util.List;
import java.util.Map;

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

import neu.edu.service.RoleService;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('Admin')")
	//public ResponseEntity<String> createRole(@PathVariable("userId") Integer userId, @RequestBody @Valid RoleModel roleModel) {
	public ResponseEntity<String> createRole(/*@PathVariable("userId") Integer userId,*/ @RequestBody @Valid RoleModel roleModel) {
	ResponseEntity<String> response = new ResponseEntity<String>("Role Not Created",
				HttpStatus.UNPROCESSABLE_ENTITY);
//		if (roleService.createRole(userId, roleModel)) {
//			response = new ResponseEntity<String>(HttpStatus.OK);
//		}
		if (roleService.createRole(roleModel)) {
			response = new ResponseEntity<String>(HttpStatus.OK);
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.GET)
	//@PreAuthorize("hasAuthority('Guest')")
	public List<RoleModel> findAll() {
		return roleService.findAll();
	}

	@RequestMapping(path = "/{roleId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRole(@PathVariable("userId") Integer userId, @PathVariable("roleId") Integer roleId) {
		ResponseEntity<?> response = new ResponseEntity<>("Role Not Deleted", HttpStatus.UNPROCESSABLE_ENTITY);

		boolean deleteStatus = roleService.deleteRole(userId, roleId);
		if (deleteStatus) {
			response = new ResponseEntity<>(deleteStatus, HttpStatus.OK);
		}
		return response;
	}

	@RequestMapping(path = "/{roleId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRole(@PathVariable("userId") Integer userId, @PathVariable("roleId") Integer roleId,
			@RequestBody @Valid RoleModel newRole) {
		ResponseEntity<?> response = new ResponseEntity<>("Role Not Updated", HttpStatus.UNPROCESSABLE_ENTITY);

		boolean deleteStatus = roleService.updateRole(userId, roleId, newRole);
		if (deleteStatus) {
			response = new ResponseEntity<>(deleteStatus, HttpStatus.OK);
		}
		return response;
	}

}
