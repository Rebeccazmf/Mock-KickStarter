package neu.edu.entity;
// Generated Nov 28, 2017 3:19:56 PM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * StartupService generated by hbm2java
 */
@Entity
@Table(name = "startup_service", catalog = "Info6250Final")
public class StartupService implements java.io.Serializable {

	private Integer requestId;
	private Idea idea;
	private Date requestStartdate;
	private String requestBaseamount;
	private Date requestEnddate;
	private String requestDescription;
	private String requestStatus;
	private Set<BidRecord> bidRecords = new HashSet<BidRecord>(0);

	public StartupService() {
	}

	public StartupService(Date requestStartdate, String requestBaseamount, Date requestEnddate, String requestStatus) {
		this.requestStartdate = requestStartdate;
		this.requestBaseamount = requestBaseamount;
		this.requestEnddate = requestEnddate;
		this.requestStatus = requestStatus;
	}

	public StartupService(Idea idea, Date requestStartdate, String requestBaseamount, Date requestEnddate,
			String requestDescription, String requestStatus, Set<BidRecord> bidRecords) {
		this.idea = idea;
		this.requestStartdate = requestStartdate;
		this.requestBaseamount = requestBaseamount;
		this.requestEnddate = requestEnddate;
		this.requestDescription = requestDescription;
		this.requestStatus = requestStatus;
		this.bidRecords = bidRecords;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "request_id", unique = true, nullable = false)
	public Integer getRequestId() {
		return this.requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idea_id")
	public Idea getIdea() {
		return this.idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "request_startdate", nullable = false, length = 19)
	public Date getRequestStartdate() {
		return this.requestStartdate;
	}

	public void setRequestStartdate(Date requestStartdate) {
		this.requestStartdate = requestStartdate;
	}

	@Column(name = "request_baseamount", nullable = false, length = 20)
	public String getRequestBaseamount() {
		return this.requestBaseamount;
	}

	public void setRequestBaseamount(String requestBaseamount) {
		this.requestBaseamount = requestBaseamount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "request_enddate", nullable = false, length = 19)
	public Date getRequestEnddate() {
		return this.requestEnddate;
	}

	public void setRequestEnddate(Date requestEnddate) {
		this.requestEnddate = requestEnddate;
	}

	@Column(name = "request_description", length = 5000)
	public String getRequestDescription() {
		return this.requestDescription;
	}

	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}

	@Column(name = "request_status", nullable = false, length = 20)
	public String getRequestStatus() {
		return this.requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "startupService")
	public Set<BidRecord> getBidRecords() {
		return this.bidRecords;
	}

	public void setBidRecords(Set<BidRecord> bidRecords) {
		this.bidRecords = bidRecords;
	}

}
