package com.ward.savefood.admin.model;

import javax.validation.constraints.NotNull;

public class UpdateFoodRequest {
	
	@NotNull
	private int foodSeq;
	@NotNull
	private String foodName;
	@NotNull
	private int foodExpiDateFrozen;
	@NotNull
	private int foodExpiDate;
	@NotNull
	private int foodExpiDateRoom;
	private String foodImg;
	
	public int getFoodSeq() {
		return foodSeq;
	}
	public void setFoodSeq(int foodSeq) {
		this.foodSeq = foodSeq;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
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
	public String getFoodImg() {
		return foodImg;
	}
	public void setFoodImg(String foodImg) {
		this.foodImg = foodImg;
	}
	@Override
	public String toString() {
		return "UpdateFoodRequest [foodSeq=" + foodSeq + ", foodName=" + foodName + ", foodExpiDateFrozen="
				+ foodExpiDateFrozen + ", foodExpiDate=" + foodExpiDate + ", foodExpiDateRoom=" + foodExpiDateRoom
				+ ", foodImg=" + foodImg + "]";
	}

}
