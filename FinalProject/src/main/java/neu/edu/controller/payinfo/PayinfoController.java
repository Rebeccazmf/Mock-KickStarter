package neu.edu.controller.payinfo;

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

import neu.edu.service.PayinfoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
//@RequestMapping("/{userId:[0-9]}/Payinfo")
@RequestMapping("/payinfo")
public class PayinfoController {
	@Autowired
	private PayinfoService payinfoService;
	
	@RequestMapping(path = "/user/{userId}",method = RequestMethod.POST)
	public ResponseEntity<String> createPayinfo(@PathVariable("userId") Integer userId, @RequestBody @Valid PayinfoModel payinfoModel) {

		ResponseEntity<String> response = new ResponseEntity<String>("pay info Not Created",
				HttpStatus.UNPROCESSABLE_ENTITY);
		if (payinfoService.createPayinfo(userId, payinfoModel)) {
			response = new ResponseEntity<String>(HttpStatus.OK);
		}

		return response;
	}
}
