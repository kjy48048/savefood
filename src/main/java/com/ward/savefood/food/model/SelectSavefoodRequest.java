package com.ward.savefood.food.model;

import javax.validation.constraints.NotNull;

public class SelectSavefoodRequest {
	
	@NotNull
	private int savefoodSeq;
	private int storageCode;
	private String memberSeq;
	
	public int getSavefoodSeq() {
		return savefoodSeq;
	}
	public void setSavefoodSeq(int savefoodSeq) {
		this.savefoodSeq = savefoodSeq;
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
	@Override
	public String toString() {
		return "SelectSavefoodRequest [savefoodSeq=" + savefoodSeq + ", storageCode=" + storageCode + ", memberSeq="
				+ memberSeq + "]";
	}
}
