package neu.edu.controller.bidrecord;

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

import neu.edu.controller.purchase.PurchaseModel;
import neu.edu.controller.role.RoleModel;
import neu.edu.service.BidRecordService;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
//@RequestMapping("/user/{userId:[0-9]}/service")
@RequestMapping("/bid")
public class BidRecordController {
	@Autowired
	private BidRecordService bidRecordService;

	@RequestMapping(method = RequestMethod.GET)
	public List<BidRecordModel> findAll() {
		return bidRecordService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('Startup')")
	public ResponseEntity<String> createBid(/*@PathVariable("userId") Integer userId, */@RequestBody @Valid BidRecordModel bidModel) {

		ResponseEntity<String> response = new ResponseEntity<String>("Bid Not Created",
				HttpStatus.UNPROCESSABLE_ENTITY);
		if (bidRecordService.createBid(/*userId,*/bidModel)) {
			response = new ResponseEntity<String>(HttpStatus.OK);
		}

		return response;
	}
	
	//Creator award a bid for a service
	@RequestMapping(path = "/{bidId}", method = RequestMethod.PUT)
	@PreAuthorize("hasAuthority('Startup') or hasAuthority('Creator')")
	public ResponseEntity<?> updateBidStatus(@PathVariable("bidId") Integer bidId,
			@RequestBody @Valid BidRecordModel bidModel) {
		ResponseEntity<?> response = new ResponseEntity<>("Bid Not Updated", HttpStatus.UNPROCESSABLE_ENTITY);

		boolean updateStatus = bidRecordService.updateBidStatus(bidId, bidModel);
		if (updateStatus) {
			response = new ResponseEntity<>(updateStatus, HttpStatus.OK);
		}
		return response;
	}
	
	//Startup views accepted services from different Ideas.	
	@RequestMapping(path = "/idea/{ideaName}", method = RequestMethod.GET)
	public List<BidRecordModel> findByStatus(@PathVariable("ideaName") String ideaName) {
		return	bidRecordService.findByStatusService(ideaName);
	}
	@RequestMapping(path = "/user/{userId}", method = RequestMethod.GET)
	public List<BidRecordModel> findByCreator(@PathVariable("userId") Integer userId) {
		return	bidRecordService.findByCreator(userId);
	}

}
