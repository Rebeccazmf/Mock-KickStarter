package neu.edu.controller.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ServiceModel {
	private Integer serviceId;
	
	private Integer ideaId;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	private float baseAmount;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	private String serviceDesc;
	private String serviceStatus;
	
	
	public ServiceModel(Integer serviceId) {
		super();
		this.serviceId = serviceId;
	}
	public ServiceModel() {
		super();
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getIdeaId() {
		return ideaId;
	}
	public void setIdeaId(Integer ideaId) {
		this.ideaId = ideaId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public float getBaseAmount() {
		return baseAmount;
	}
	public void setBaseAmount(float baseAmount) {
		this.baseAmount = baseAmount;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getServiceDesc() {
		return serviceDesc;
	}
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	public String getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	
	
	
}
