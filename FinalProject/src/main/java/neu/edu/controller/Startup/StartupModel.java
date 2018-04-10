package neu.edu.controller.Startup;

import neu.edu.entity.Category;
import neu.edu.entity.User;

public class StartupModel {
	private Integer startupId;
	private Integer categoryId;
	private Integer userId;
//	private String startupName;
//	private String startupDesc;
	public StartupModel() {
		super();
	}
	public StartupModel(Integer startupId) {
		super();
		this.startupId = startupId;
	}
	public Integer getStartupId() {
		return startupId;
	}
	public void setStartupId(Integer startupId) {
		this.startupId = startupId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
