package neu.edu.entity;
// Generated Nov 28, 2017 3:19:56 PM by Hibernate Tools 5.2.3.Final

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
import javax.persistence.UniqueConstraint;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "Info6250Final", uniqueConstraints = @UniqueConstraint(columnNames = "user_username"))
public class User implements java.io.Serializable {

	private Integer userId;
	private Role role;
	private String userUsername;
	private String userPassword;
	private float userBalance;
	private Set<Idea> ideas = new HashSet<Idea>(0);
	private Set<ShoppingCart> shoppingCarts = new HashSet<ShoppingCart>(0);
	private Set<PurchaseRecord> purchaseRecords = new HashSet<PurchaseRecord>(0);

	public User() {
	}

	public User(Role role, String userUsername, String userPassword, float userBalance) {
		this.role = role;
		this.userUsername = userUsername;
		this.userPassword = userPassword;
		this.userBalance = userBalance;
	}

	public User(Role role, String userUsername, String userPassword, float userBalance, Set<Idea> ideas,
			Set<ShoppingCart> shoppingCarts, Set<PurchaseRecord> purchaseRecords) {
		this.role = role;
		this.userUsername = userUsername;
		this.userPassword = userPassword;
		this.userBalance = userBalance;
		this.ideas = ideas;
		this.shoppingCarts = shoppingCarts;
		this.purchaseRecords = purchaseRecords;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "user_username", unique = true, nullable = false, length = 50)
	public String getUserUsername() {
		return this.userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	@Column(name = "user_password", nullable = false, length = 50)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "user_balance", nullable = false, precision = 12, scale = 0)
	public float getUserBalance() {
		return this.userBalance;
	}

	public void setUserBalance(float userBalance) {
		this.userBalance = userBalance;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Idea> getIdeas() {
		return this.ideas;
	}

	public void setIdeas(Set<Idea> ideas) {
		this.ideas = ideas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<ShoppingCart> getShoppingCarts() {
		return this.shoppingCarts;
	}

	public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<PurchaseRecord> getPurchaseRecords() {
		return this.purchaseRecords;
	}

	public void setPurchaseRecords(Set<PurchaseRecord> purchaseRecords) {
		this.purchaseRecords = purchaseRecords;
	}

}
