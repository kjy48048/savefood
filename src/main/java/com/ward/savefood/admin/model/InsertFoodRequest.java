package com.ward.savefood.admin.model;

import javax.validation.constraints.NotBlank;

public class InsertFoodRequest {
	
	@NotBlank
	private String foodName;
	@NotBlank
	private int foodExpiDate;
	@NotBlank
	private int categorySeq;
	
	public String getFoodName() {
		return foodName;
	}
	
	public int getFoodExpiDate() {
		return foodExpiDate;
	}
	
	public int getCategorySeq() {
		return categorySeq;
	}
	
	public String getSearchText(){
		return foodName.replaceAll("[ ,/&]", "|");		
	}

	@Override
	public String toString() {
		return "InsertFoodRequest [foodName=" + foodName + ", foodExpiDate=" + foodExpiDate + ", categorySeq="
				+ categorySeq + "]";
	}
}
