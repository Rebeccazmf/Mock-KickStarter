package neu.edu.controller.purchase;

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

import neu.edu.controller.role.RoleModel;
import neu.edu.controller.service.ServiceModel;
import neu.edu.service.PurchaseService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
//@RequestMapping("/user/{userId:[0-9]}/role")
@RequestMapping("/purchase")
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<PurchaseModel> findAll() {
		return purchaseService.findAll();
	}
	//View List of all funding’s done.
	@RequestMapping(path = "/{status}", method = RequestMethod.GET)
	public List<PurchaseModel> findByStatus(@PathVariable("status") String status) {
		return	purchaseService.findByStatus(status);
	}
	
	//Add view Details of Ideas (Funding List)
	@RequestMapping(path = "/idea/{ideaId}", method = RequestMethod.GET)
	public List<PurchaseModel> findByStatus(@PathVariable("ideaId") Integer ideaId, @RequestBody @Valid PurchaseModel purchaseModel) {
		return	purchaseService.findByIdea(ideaId, purchaseModel);
	}

	@RequestMapping(path = "/user/{userId}", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('Guest') or hasAuthority('Funder')")
	public ResponseEntity<String> createService(@PathVariable("userId") Integer userId, @RequestBody @Valid PurchaseModel purchaseModel) {

		ResponseEntity<String> response = new ResponseEntity<String>("Purchase Not Created",
				HttpStatus.UNPROCESSABLE_ENTITY);
		//userId = 6;
		if (purchaseService.createPurchase(userId, purchaseModel)) {
			response = new ResponseEntity<String>(HttpStatus.OK);
		}
		return response;
	}
}
