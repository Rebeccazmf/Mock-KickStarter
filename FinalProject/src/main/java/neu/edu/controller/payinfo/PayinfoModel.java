package neu.edu.controller.payinfo;

import neu.edu.entity.User;

public class PayinfoModel {
	private Integer payinfoId;
	private Integer userId;
	private String cardNo;
	private String cvv;
	private String expDate;
	public PayinfoModel() {
		super();
	}
	public PayinfoModel(Integer payinfoId) {
		super();
		this.payinfoId = payinfoId;
	}
	public Integer getPayinfoId() {
		return payinfoId;
	}
	public void setPayinfoId(Integer payinfoId) {
		this.payinfoId = payinfoId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	
	
}
