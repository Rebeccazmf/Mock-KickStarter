package neu.edu.controller.idea;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import neu.edu.controller.user.UserModel;
import neu.edu.entity.Category;
import neu.edu.entity.User;

public class IdeaModel {
	private Integer ideaId;
	private Integer categoryId;
	private Integer userId;
	
	@NotNull
	private String ideaName;
	private String ideaDesc;
	private float targetAmount;
	private float gatheredAmount;
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	private String ideaStatus;
	
	
	public IdeaModel() {
		super();
	}
	
	public IdeaModel(Integer ideaId) {
		super();
		this.ideaId = ideaId;
	}

	public Integer getIdeaId() {
		return ideaId;
	}
	public void setIdeaId(Integer ideaId) {
		this.ideaId = ideaId;
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

	public String getIdeaName() {
		return ideaName;
	}
	public void setIdeaName(String ideaName) {
		this.ideaName = ideaName;
	}
	public String getIdeaDesc() {
		return ideaDesc;
	}
	public void setIdeaDesc(String ideaDesc) {
		this.ideaDesc = ideaDesc;
	}
	public float getTargetAmount() {
		return targetAmount;
	}
	public void setTargetAmount(float targetAmount) {
		this.targetAmount = targetAmount;
	}
	public float getGatheredAmount() {
		return gatheredAmount;
	}
	public void setGatheredAmount(float gatheredAmount) {
		this.gatheredAmount = gatheredAmount;
	}

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getIdeaStatus() {
		return ideaStatus;
	}
	public void setIdeaStatus(String ideaStatus) {
		this.ideaStatus = ideaStatus;
	}
	
	
	
}
