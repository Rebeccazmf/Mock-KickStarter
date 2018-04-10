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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Category generated by hbm2java
 */
@Entity
@Table(name = "category")
public class Category implements java.io.Serializable {

	private Integer categoryId;
	private String categoryName;
	private String categoryDesc;
	private Set<Startup> startups = new HashSet<Startup>(0);
	private Set<Idea> ideas = new HashSet<Idea>(0);

	public Category() {
	}

	public Category(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category(String categoryName, String categoryDesc, Set<Startup> startups, Set<Idea> ideas) {
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.startups = startups;
		this.ideas = ideas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "category_id", unique = true, nullable = false)
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "category_name", nullable = false, length = 50)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "category_desc", length = 200)
	public String getCategoryDesc() {
		return this.categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Startup> getStartups() {
		return this.startups;
	}

	public void setStartups(Set<Startup> startups) {
		this.startups = startups;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Idea> getIdeas() {
		return this.ideas;
	}

	public void setIdeas(Set<Idea> ideas) {
		this.ideas = ideas;
	}

}