package neu.edu.controller.shoppingcart;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import neu.edu.entity.IdeaOption;
import neu.edu.entity.User;

public class ShoppingCartModel {

	private Integer shoppingCartId;
	private Integer optionId;
	private Integer userId;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date addedOnDate;
	
	
	public ShoppingCartModel() {
		super();
	}

	public ShoppingCartModel(Integer shoppingCartId) {
		super();
		this.shoppingCartId = shoppingCartId;
	}

	public Integer getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(Integer shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getAddedOnDate() {
		return addedOnDate;
	}

	public void setAddedOnDate(Date addedOnDate) {
		this.addedOnDate = addedOnDate;
	}
	
	
}
