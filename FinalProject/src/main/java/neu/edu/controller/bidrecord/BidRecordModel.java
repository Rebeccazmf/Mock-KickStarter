package neu.edu.controller.bidrecord;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BidRecordModel {
	private Integer bidId;
	private Integer serviceId;
	private Integer startupId;
	
	private String bidStatus;
	
	@NotNull
	private String bidDesc;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	private float bidAmount;
	//private String bidRecordStatus;
	
	
	public BidRecordModel() {
		super();
	}
	
	
	public BidRecordModel(Integer bidId) {
		super();
		this.bidId = bidId;
	}


	public Integer getBidId() {
		return bidId;
	}
	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}
	
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getStartupId() {
		return startupId;
	}
	public void setStartupId(Integer startupId) {
		this.startupId = startupId;
	}
	public String getBidStatus() {
		return bidStatus;
	}
	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
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
	public float getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(float bidAmount) {
		this.bidAmount = bidAmount;
	}


	public String getBidDesc() {
		return bidDesc;
	}


	public void setBidDesc(String bidDesc) {
		this.bidDesc = bidDesc;
	}
	
//	public String getBidRecordStatus() {
//		return bidRecordStatus;
//	}
//	public void setBidRecordStatus(String bidRecordStatus) {
//		this.bidRecordStatus = bidRecordStatus;
//	}

	
}
