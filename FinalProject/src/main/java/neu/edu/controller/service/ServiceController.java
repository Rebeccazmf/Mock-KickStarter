package neu.edu.controller.service;

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


import neu.edu.service.ServiceService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
//@RequestMapping("/user/{userId:[0-9]}/service")
@RequestMapping("/service")
public class ServiceController {
	@Autowired
	private ServiceService serviceService;

	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('Creator')")
	public ResponseEntity<String> createService(/*@PathVariable("userId") Integer userId, */@RequestBody @Valid ServiceModel serviceModel) {

		ResponseEntity<String> response = new ResponseEntity<String>("Service Not Created",
				HttpStatus.UNPROCESSABLE_ENTITY);
		if (serviceService.createService(/*userId, */serviceModel)) {
			response = new ResponseEntity<String>(HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<ServiceModel> findAll() {
		return serviceService.findAll();
	}
	
	//admin View Details of Ideas (Services List)
	@RequestMapping(path = "/list/{ideaId}", method = RequestMethod.GET)
	public List<ServiceModel> findByIdea(@PathVariable("ideaId") Integer ideaId) {
		return serviceService.findByIdea(ideaId);
	}
	
	//Startup views accepted services from different Ideas.
		@RequestMapping(path = "/accept/{ideaId}", method = RequestMethod.GET)
		public List<ServiceModel> findByIdeaStatus(@PathVariable("ideaId") Integer ideaId, @RequestBody @Valid ServiceModel serviceModel) {
			return	serviceService.findByIdeaStatus(ideaId, serviceModel);
		}
		@RequestMapping(path = "/creator/{userId}", method = RequestMethod.GET)
		public List<ServiceModel> findByCreator(@PathVariable("userId") Integer userId) {
			return	serviceService.findByCreator(userId);
		}
		@RequestMapping(path = "/startup/{userId}", method = RequestMethod.GET)
		public List<ServiceModel> findByStartup(@PathVariable("userId") Integer userId) {
			return	serviceService.findByStartup(userId);
		}

	
}
