package com.ward.savefood.food.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class InsertUserFoodRequest {
	
	@NotNull
	private int foodSeq;
	@NotNull
	private int saveplaceSeq;
	private int storageCode;
	private String memberSeq;
	private String savefoodName;
	private String savefoodQuantity;
	private Date savefoodExpiDate;
	
	public int getFoodSeq() {
		return foodSeq;
	}
	public void setFoodSeq(int foodSeq) {
		this.foodSeq = foodSeq;
	}
	public int getSaveplaceSeq() {
		return saveplaceSeq;
	}
	public void setSaveplaceSeq(int saveplaceSeq) {
		this.saveplaceSeq = saveplaceSeq;
	}
	public int getStorageCode() {
		return storageCode;
	}
	public void setStorageCode(int storageCode) {
		this.storageCode = storageCode;
	}
	public String getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(String memberSeq) {
		this.memberSeq = memberSeq;
	}
	public String getSavefoodName() {
		return savefoodName;
	}
	public void setSavefoodName(String savefoodName) {
		this.savefoodName = savefoodName;
	}
	public String getSavefoodQuantity() {
		return savefoodQuantity;
	}
	public void setSavefoodQuantity(String savefoodQuantity) {
		this.savefoodQuantity = savefoodQuantity;
	}
	public Date getSavefoodExpiDate() {
		return savefoodExpiDate;
	}
	public void setSavefoodExpiDate(Date savefoodExpiDate) {
		this.savefoodExpiDate = savefoodExpiDate;
	}
	@Override
	public String toString() {
		return "InsertUserFoodRequest [foodSeq=" + foodSeq + ", saveplaceSeq=" + saveplaceSeq + ", storageCode="
				+ storageCode + ", memberSeq=" + memberSeq + ", savefoodName=" + savefoodName + ", savefoodQuantity="
				+ savefoodQuantity + ", savefoodExpiDate=" + savefoodExpiDate + "]";
	}	
}
