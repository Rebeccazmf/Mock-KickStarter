package neu.edu.controller.completionrecord;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import neu.edu.entity.StartupService;

public class CompletionRecordModel {
	private Integer recordId;
	private Integer serviceId;
	private String percentage;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date updateDate;
	
	public CompletionRecordModel() {
		super();
	}
	
	public CompletionRecordModel(Integer recordId) {
		super();
		this.recordId = recordId;
	}

	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
