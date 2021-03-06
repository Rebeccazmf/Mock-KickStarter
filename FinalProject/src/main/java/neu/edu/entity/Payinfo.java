package neu.edu.entity;
// default package
// Generated Dec 9, 2017 1:25:52 AM by Hibernate Tools 5.2.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Payinfo generated by hbm2java
 */
@Entity
@Table(name = "payinfo")
public class Payinfo implements java.io.Serializable {

	private Integer payinfoId;
	private User user;
	private String cardNo;
	private String cvv;
	private String expDate;

	public Payinfo() {
	}

	public Payinfo(User user, String cardNo, String cvv, String expDate) {
		this.user = user;
		this.cardNo = cardNo;
		this.cvv = cvv;
		this.expDate = expDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "payinfo_id", unique = true, nullable = false)
	public Integer getPayinfoId() {
		return this.payinfoId;
	}

	public void setPayinfoId(Integer payinfoId) {
		this.payinfoId = payinfoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "card_no", nullable = false, length = 300)
	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(name = "cvv", nullable = false, length = 300)
	public String getCvv() {
		return this.cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	@Column(name = "exp_date", nullable = false, length = 300)
	public String getExpDate() {
		return this.expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

}
