package com.ward.savefood.admin.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertFoodRequest {
	
	@NotBlank
	private String foodName;
	@NotNull
	private int foodExpiDateFrozen;
	@NotNull
	private int foodExpiDate;
	@NotNull
	private int foodExpiDateRoom;
	@NotBlank
	private int categorySeq;
	
	public String getFoodName() {
		return foodName;
	}
	
	public int getFoodExpiDateFrozen() {
		return foodExpiDateFrozen;
	}


	public void setFoodExpiDateFrozen(int foodExpiDateFrozen) {
		this.foodExpiDateFrozen = foodExpiDateFrozen;
	}


	public int getFoodExpiDate() {
		return foodExpiDate;
	}


	public void setFoodExpiDate(int foodExpiDate) {
		this.foodExpiDate = foodExpiDate;
	}


	public int getFoodExpiDateRoom() {
		return foodExpiDateRoom;
	}


	public void setFoodExpiDateRoom(int foodExpiDateRoom) {
		this.foodExpiDateRoom = foodExpiDateRoom;
	}


	public int getCategorySeq() {
		return categorySeq;
	}
	
	public String getSearchText(){
		return foodName.replaceAll("[ ,/&]", "|");		
	}

	@Override
	public String toString() {
		return "InsertFoodRequest [foodName=" + foodName + ", foodExpiDateFrozen=" + foodExpiDateFrozen
				+ ", foodExpiDate=" + foodExpiDate + ", foodExpiDateRoom=" + foodExpiDateRoom + ", categorySeq="
				+ categorySeq + "]";
	}
}
