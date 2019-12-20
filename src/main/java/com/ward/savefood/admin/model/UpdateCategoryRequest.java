package com.ward.savefood.admin.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateCategoryRequest {
	
	@NotNull
	private int categorySeq;
	@NotBlank
	private String categoryName;
	
	
	public int getCategorySeq() {
		return categorySeq;
	}

	public void setCategorySeq(int categorySeq) {
		this.categorySeq = categorySeq;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	@Override
	public String toString() {
		return "UpdateCategoryRequest [categorySeq=" + categorySeq + ", categoryName=" + categoryName + "]";
	}
}
