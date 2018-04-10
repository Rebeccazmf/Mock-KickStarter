package neu.edu.entity;
// default package
// Generated Dec 7, 2017 12:18:31 AM by Hibernate Tools 5.2.3.Final

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

/**
 * Startup generated by hbm2java
 */
@Entity
@Table(name = "startup")
public class Startup implements java.io.Serializable {

	private Integer startupId;
	private Category category;
	private User user;
	private Set<BidRecord> bidRecords = new HashSet<BidRecord>(0);

	public Startup() {
	}

	public Startup(Category category, User user) {
		this.category = category;
		this.user = user;
	}

	public Startup(Category category, User user, Set<BidRecord> bidRecords) {
		this.category = category;
		this.user = user;
		this.bidRecords = bidRecords;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "startup_id", unique = true, nullable = false)
	public Integer getStartupId() {
		return this.startupId;
	}

	public void setStartupId(Integer startupId) {
		this.startupId = startupId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "startup")
	public Set<BidRecord> getBidRecords() {
		return this.bidRecords;
	}

	public void setBidRecords(Set<BidRecord> bidRecords) {
		this.bidRecords = bidRecords;
	}

}
