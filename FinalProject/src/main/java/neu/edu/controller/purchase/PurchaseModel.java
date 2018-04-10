package neu.edu.controller.purchase;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PurchaseModel {
	
	private Integer purchaseId;
	private Integer ideaOptionId;
	private Integer userId;
	private Integer purchaseAmount;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date purchaseDate;
	
	private String purchaseStatus;
	public PurchaseModel() {
		super();
	}
	
	public PurchaseModel(Integer purchaseId) {
		super();
		this.purchaseId = purchaseId;
	}

	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Integer getIdeaOptionId() {
		return ideaOptionId;
	}
	public void setIdeaOptionId(Integer ideaOptionId) {
		this.ideaOptionId = ideaOptionId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(Integer purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPurchaseStatus() {
		return purchaseStatus;
	}
	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}
	
	
}
