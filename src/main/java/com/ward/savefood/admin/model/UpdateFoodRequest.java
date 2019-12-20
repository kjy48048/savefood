package com.ward.savefood.admin.model;

import javax.validation.constraints.NotNull;

public class UpdateFoodRequest {
	
	@NotNull
	private int foodSeq;
	@NotNull
	private String foodName;
	@NotNull
	private int foodExpiDate;
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
	public int getFoodExpiDate() {
		return foodExpiDate;
	}
	public void setFoodExpiDate(int foodExpiDate) {
		this.foodExpiDate = foodExpiDate;
	}
	public String getFoodImg() {
		return foodImg;
	}
	public void setFoodImg(String foodImg) {
		this.foodImg = foodImg;
	}
	@Override
	public String toString() {
		return "UpdateFoodRequest [foodSeq=" + foodSeq + ", foodName=" + foodName + ", foodExpiDate=" + foodExpiDate
				+ ", foodImg=" + foodImg + "]";
	}
}
