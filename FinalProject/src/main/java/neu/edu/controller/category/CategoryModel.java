package neu.edu.controller.category;

import javax.validation.constraints.NotNull;

public class CategoryModel {
	private Integer categoryId;
	@NotNull
	private String categoryName;
	private String categoryDesc;
	
	
	public CategoryModel() {
		super();
	}

	public CategoryModel(Integer categoryId) {
		super();
		this.categoryId = categoryId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	
	
}
