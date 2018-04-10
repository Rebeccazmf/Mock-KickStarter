package neu.edu.controller.ideaoption;

import neu.edu.entity.Idea;

public class IdeaOptionModel {

	private Integer optionId;
	private Integer ideaId;
	private String optionDesc;
	private float optionPrice;
	private float maxFunding;
	private int boughtAmount;
	
	public IdeaOptionModel() {
		super();
	}
	
	public IdeaOptionModel(Integer optionId) {
		super();
		this.optionId = optionId;
	}

	public Integer getOptionId() {
		return optionId;
	}
	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}
	public Integer getIdeaId() {
		return ideaId;
	}
	public void setIdeaId(Integer ideaId) {
		this.ideaId = ideaId;
	}
	public String getOptionDesc() {
		return optionDesc;
	}
	public void setOptionDesc(String optionDesc) {
		this.optionDesc = optionDesc;
	}
	public float getOptionPrice() {
		return optionPrice;
	}
	public void setOptionPrice(float optionPrice) {
		this.optionPrice = optionPrice;
	}
	public float getMaxFunding() {
		return maxFunding;
	}

	public void setMaxFunding(float maxFunding) {
		this.maxFunding = maxFunding;
	}

	public int getBoughtAmount() {
		return boughtAmount;
	}
	public void setBoughtAmount(int boughtAmount) {
		this.boughtAmount = boughtAmount;
	}
	
}
