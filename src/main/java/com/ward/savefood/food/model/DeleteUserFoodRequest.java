package com.ward.savefood.food.model;

import javax.validation.constraints.NotNull;

public class DeleteUserFoodRequest {
	
	@NotNull
	private int savefoodSeq;
	private String memberSeq;
	
	public int getSavefoodSeq() {
		return savefoodSeq;
	}
	public void setSavefoodSeq(int savefoodSeq) {
		this.savefoodSeq = savefoodSeq;
	}
	public String getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(String memberSeq) {
		this.memberSeq = memberSeq;
	}
	
	@Override
	public String toString() {
		return "DeleteUserFoodRequest [savefoodSeq=" + savefoodSeq + ", memberSeq=" + memberSeq + "]";
	}
}
