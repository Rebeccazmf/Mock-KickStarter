package neu.edu.controller.ideaoption;

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
import neu.edu.entity.IdeaOption;
import neu.edu.service.IdeaOptionService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
//@RequestMapping("/user/{userId:[0-9]}/IdeaOption")
@RequestMapping("/ideaOption")
public class IdeaOptionController {
	@Autowired
	private IdeaOptionService ideaOptionService;
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('Creator')")
	public ResponseEntity<String> createIdeaOption(/*@PathVariable("userId") Integer userId,*/ @RequestBody @Valid IdeaOptionModel optionModel) {

		ResponseEntity<String> response = new ResponseEntity<String>("Idea Option Not Created",
				HttpStatus.UNPROCESSABLE_ENTITY);
		if (ideaOptionService.createIdeaOption(/*userId,*/optionModel)) {
			response = new ResponseEntity<String>(HttpStatus.OK);
		}

		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<IdeaOptionModel> findAll() {
		return ideaOptionService.findAll();
	}
	@RequestMapping(path = "/{ideaId}",method = RequestMethod.GET)
	public List<IdeaOptionModel> findByIdea(@PathVariable("ideaId") Integer ideaId) {
		return ideaOptionService.findByIdea(ideaId);
	}
	@RequestMapping(path = "/creator/{userId}",method = RequestMethod.GET)
	public List<IdeaOptionModel> findByCreator(@PathVariable("userId") Integer userId) {
		return ideaOptionService.findByCreator(userId);
	}

}
