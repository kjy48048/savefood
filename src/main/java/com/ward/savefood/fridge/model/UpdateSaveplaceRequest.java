package com.ward.savefood.fridge.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateSaveplaceRequest {
	
	@NotNull
	private int saveplaceSeq;
	@NotNull
	private String saveplaceName;
	
	public int getSaveplaceSeq() {
		return saveplaceSeq;
	}
	public void setSaveplaceSeq(int saveplaceSeq) {
		this.saveplaceSeq = saveplaceSeq;
	}
	public String getSaveplaceName() {
		return saveplaceName;
	}
	public void setSaveplaceName(String saveplaceName) {
		this.saveplaceName = saveplaceName;
	}
	@Override
	public String toString() {
		return "UpdateSaveplaceRequest [saveplaceSeq=" + saveplaceSeq + ", saveplaceName=" + saveplaceName + "]";
	}
}