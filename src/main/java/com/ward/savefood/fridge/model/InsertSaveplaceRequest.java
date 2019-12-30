package com.ward.savefood.fridge.model;

import javax.validation.constraints.NotBlank;

public class InsertSaveplaceRequest {
	
	@NotBlank
	private int fridgeSeq;
	@NotBlank
	private String saveplace;
	@NotBlank
	private int storage;
	
	public int getFridgeSeq() {
		return fridgeSeq;
	}

	public String getSaveplace() {
		return saveplace;
	}

	public int getStorage() {
		return storage;
	}

	@Override
	public String toString() {
		return "InsertSaveplaceRequest [fridgeSeq=" + fridgeSeq + ", saveplace=" + saveplace + ", storage="
				+ storage + "]";
	}
}
