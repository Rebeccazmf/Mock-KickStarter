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
 * Idea generated by hbm2java
 */
@Entity
@Table(name = "idea", catalog = "Info6250Final")
public class Idea implements java.io.Serializable {

	private Integer ideaId;
	private Category category;
	private User user;
	private float ideaTargeramount;
	private float ideaGatheredAmount;
	private Date ideaStartdate;
	private Date ideaEnddate;
	private String ideaStatus;
	private String ideaDescription;
	private Set<IdeaOption> ideaOptions = new HashSet<IdeaOption>(0);
	private Set<StartupService> startupServices = new HashSet<StartupService>(0);

	public Idea() {
	}

	public Idea(User user, float ideaTargeramount, float ideaGatheredAmount, Date ideaStartdate, Date ideaEnddate,
			String ideaStatus) {
		this.user = user;
		this.ideaTargeramount = ideaTargeramount;
		this.ideaGatheredAmount = ideaGatheredAmount;
		this.ideaStartdate = ideaStartdate;
		this.ideaEnddate = ideaEnddate;
		this.ideaStatus = ideaStatus;
	}

	public Idea(Category category, User user, float ideaTargeramount, float ideaGatheredAmount, Date ideaStartdate,
			Date ideaEnddate, String ideaStatus, String ideaDescription, Set<IdeaOption> ideaOptions,
			Set<StartupService> startupServices) {
		this.category = category;
		this.user = user;
		this.ideaTargeramount = ideaTargeramount;
		this.ideaGatheredAmount = ideaGatheredAmount;
		this.ideaStartdate = ideaStartdate;
		this.ideaEnddate = ideaEnddate;
		this.ideaStatus = ideaStatus;
		this.ideaDescription = ideaDescription;
		this.ideaOptions = ideaOptions;
		this.startupServices = startupServices;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idea_id", unique = true, nullable = false)
	public Integer getIdeaId() {
		return this.ideaId;
	}

	public void setIdeaId(Integer ideaId) {
		this.ideaId = ideaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
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

	@Column(name = "idea_targeramount", nullable = false, precision = 12, scale = 0)
	public float getIdeaTargeramount() {
		return this.ideaTargeramount;
	}

	public void setIdeaTargeramount(float ideaTargeramount) {
		this.ideaTargeramount = ideaTargeramount;
	}

	@Column(name = "idea_gathered_amount", nullable = false, precision = 12, scale = 0)
	public float getIdeaGatheredAmount() {
		return this.ideaGatheredAmount;
	}

	public void setIdeaGatheredAmount(float ideaGatheredAmount) {
		this.ideaGatheredAmount = ideaGatheredAmount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "idea_startdate", nullable = false, length = 19)
	public Date getIdeaStartdate() {
		return this.ideaStartdate;
	}

	public void setIdeaStartdate(Date ideaStartdate) {
		this.ideaStartdate = ideaStartdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "idea_enddate", nullable = false, length = 19)
	public Date getIdeaEnddate() {
		return this.ideaEnddate;
	}

	public void setIdeaEnddate(Date ideaEnddate) {
		this.ideaEnddate = ideaEnddate;
	}

	@Column(name = "idea_status", nullable = false, length = 20)
	public String getIdeaStatus() {
		return this.ideaStatus;
	}

	public void setIdeaStatus(String ideaStatus) {
		this.ideaStatus = ideaStatus;
	}

	@Column(name = "idea_description", length = 4000)
	public String getIdeaDescription() {
		return this.ideaDescription;
	}

	public void setIdeaDescription(String ideaDescription) {
		this.ideaDescription = ideaDescription;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idea")
	public Set<IdeaOption> getIdeaOptions() {
		return this.ideaOptions;
	}

	public void setIdeaOptions(Set<IdeaOption> ideaOptions) {
		this.ideaOptions = ideaOptions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idea")
	public Set<StartupService> getStartupServices() {
		return this.startupServices;
	}

	public void setStartupServices(Set<StartupService> startupServices) {
		this.startupServices = startupServices;
	}

}
