package neu.edu.controller.Startup;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import neu.edu.controller.category.CategoryModel;
import neu.edu.controller.role.RoleModel;
import neu.edu.service.StartupService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
//@RequestMapping("/user/{userId:[0-9]}/startup")
@RequestMapping("/startup")
public class StartupController {
	@Autowired
	private StartupService startupService;
	//create when startup register
	@RequestMapping(path ="/user/{userId}",method = RequestMethod.POST)
	public ResponseEntity<String> createCategory(@PathVariable("userId") Integer userId, @RequestBody @Valid StartupModel startupModel) {

		ResponseEntity<String> response = new ResponseEntity<String>("Startup Not Created",
				HttpStatus.UNPROCESSABLE_ENTITY);
		if (startupService.createStartup(userId, startupModel)) {
			response = new ResponseEntity<String>(HttpStatus.OK);
		}
		return response;
	}
	@RequestMapping(method = RequestMethod.GET)
	//@PreAuthorize("hasAuthority('Guest')")
	public List<StartupModel> findAll() {
		return startupService.findAll();
	}
}
