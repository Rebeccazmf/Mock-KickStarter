package neu.edu.controller.completionrecord;

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

import neu.edu.controller.bidrecord.BidRecordModel;
import neu.edu.service.CompletionRecordService;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
//@RequestMapping("/user/{userId:[0-9]}/service")
@RequestMapping("/completionrecord")
public class CompletionRecordController {
	@Autowired
	private CompletionRecordService recordService;

	@RequestMapping(/*path = "/user/{userId}",*/method = RequestMethod.POST)
	public ResponseEntity<String> createRecord(/*@PathVariable("userId") Integer userId, */@RequestBody @Valid CompletionRecordModel recordModel) {

		ResponseEntity<String> response = new ResponseEntity<String>("Role Not Created",
				HttpStatus.UNPROCESSABLE_ENTITY);
		if (recordService.createRecord(/*userId, */recordModel)) {
			response = new ResponseEntity<String>(HttpStatus.OK);
		}

		return response;
	}
	@RequestMapping(method = RequestMethod.GET)
	public List<CompletionRecordModel> findAll(){
		return	recordService.findAll();
	}
	@RequestMapping(path = "/service/{serviceId}",method = RequestMethod.GET)
	public List<CompletionRecordModel> findByService(@PathVariable("serviceId") Integer serviceId, @RequestBody @Valid CompletionRecordModel recordModel) {
		return	recordService.findByService(serviceId,recordModel);
	}
	
	
}
