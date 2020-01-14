package com.ward.savefood.food.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class UpdateUserFoodRequest {
	
	@NotNull
	private int savefoodSeq;
	@NotNull
	private int saveplaceSeq;
	private String memberSeq;
	private String savefoodName;
	private String savefoodQuantity;
	@NotNull
	private Date savefoodExpiDate;
	
	public int getSavefoodSeq() {
		return savefoodSeq;
	}
	public void setSavefoodSeq(int savefoodSeq) {
		this.savefoodSeq = savefoodSeq;
	}
	public int getSaveplaceSeq() {
		return saveplaceSeq;
	}
	public void setSaveplaceSeq(int saveplaceSeq) {
		this.saveplaceSeq = saveplaceSeq;
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
		return "UpdateUserFoodRequest [savefoodSeq=" + savefoodSeq + ", saveplaceSeq=" + saveplaceSeq + ", memberSeq="
				+ memberSeq + ", savefoodName=" + savefoodName + ", savefoodQuantity=" + savefoodQuantity
				+ ", savefoodExpiDate=" + savefoodExpiDate + "]";
	}
}
