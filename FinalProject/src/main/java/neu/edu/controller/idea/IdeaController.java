package neu.edu.controller.idea;

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

import neu.edu.controller.bidrecord.BidRecordModel;
import neu.edu.controller.idea.IdeaModel;
import neu.edu.controller.role.RoleModel;
import neu.edu.service.IdeaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
//@RequestMapping("/user/{userId:[0-9]}/Idea")
@RequestMapping("/idea")
public class IdeaController {
	
	@Autowired
	private IdeaService ideaService;
	
	@RequestMapping(path = "/checkIdeaStatus/{userId}", method = RequestMethod.GET)
	public List<IdeaModel> checkIdeaStatus(@PathVariable("userId") Integer userId) {
		return ideaService.checkIdeaStatus(userId);
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public List<IdeaModel> findAll() {
		return ideaService.findAll();
	}
	@RequestMapping(path = "startup/{userId}",method = RequestMethod.GET)
	public List<IdeaModel> findByStartup(@PathVariable("userId") Integer userId) {
		userId = 4;
		return ideaService.findByStartup(userId);
	}
	
	//find idea by user id
	@RequestMapping(path = "user/{userId}", method = RequestMethod.GET)
	public List<IdeaModel> findIdeaByUser(@PathVariable("userId") Integer userId) {
		//userId = 4;
		return ideaService.findIdeaByUser(userId);
	}
	//startup can view ideas under their categories
	@RequestMapping(path = "category/{categoryId}",method = RequestMethod.GET)
	public List<IdeaModel> findByCategory(@PathVariable("categoryId") Integer categoryId) {
		return ideaService.findIdeaByCategory(categoryId);
	}
	
	@RequestMapping(path = "ordercategory/{userId}",method = RequestMethod.GET)
	public List<IdeaModel> findByStartupOrderByStartDate(@PathVariable("userId") Integer userId) {
		return ideaService.findByStartupOrderByStartDate(userId);
	}
	//Creator creates idea
	@RequestMapping(path = "/{userId}/create",method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('Creator')")
	public ResponseEntity<String> createIdea(@PathVariable("userId") Integer userId, @RequestBody @Valid IdeaModel ideaModel) {

		ResponseEntity<String> response = new ResponseEntity<String>("Idea Not Created",
				HttpStatus.UNPROCESSABLE_ENTITY);
		if (ideaService.createIdea(userId, ideaModel)) {
			response = new ResponseEntity<String>(HttpStatus.OK);
		}
		return response;
	}
	
	//For creator close/re-open, admin update
	@RequestMapping(path = "/{ideaId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRole(@PathVariable("userId") Integer userId, @PathVariable("ideaId") Integer ideaId,
			@RequestBody @Valid IdeaModel ideaModel) {
		ResponseEntity<?> response = new ResponseEntity<>("Idea Status Not Updated", HttpStatus.UNPROCESSABLE_ENTITY);

		boolean updateStatus = ideaService.updateStatus(userId, ideaId, ideaModel);
		if (updateStatus) {
			response = new ResponseEntity<>(updateStatus, HttpStatus.OK);
		}
		return response;
	}

}
