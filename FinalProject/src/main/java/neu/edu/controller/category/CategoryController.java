package neu.edu.controller.category;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

import neu.edu.controller.idea.IdeaModel;
import neu.edu.service.CategoryService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
//@RequestMapping("/user/{userId:[0-9]}/category")
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('Guest')")
	public List<CategoryModel> findAll() {
		return categoryService.findAll();
	}
	//Admin create category	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('Individual')")
	public ResponseEntity<String> createCategory(/*@PathVariable("userId") Integer userId,*/ @RequestBody @Valid CategoryModel categoryModel) {

		ResponseEntity<String> response = new ResponseEntity<String>("category Not Created",
				HttpStatus.UNPROCESSABLE_ENTITY);
//		if (categoryService.createCategory(userId, categoryModel)) {
//			response = new ResponseEntity<String>(HttpStatus.OK);
//		}
		if (categoryService.createCategory(categoryModel)) {
			response = new ResponseEntity<String>(HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(path = "/{categoryId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCategory(@NotNull @PathVariable("categoryId") Integer categoryId) {

		ResponseEntity<?> responseEntity = new ResponseEntity<>("Category delete Failed", HttpStatus.UNPROCESSABLE_ENTITY);
		;
		if (categoryService.deletedCategory(categoryId)) {
			responseEntity = new ResponseEntity<>("User Deleted", HttpStatus.OK);
		}
		return responseEntity;
	}

}
