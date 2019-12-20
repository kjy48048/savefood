package com.ward.savefood.admin.model;

import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.constraints.NotBlank;

public class InsertCategoryRequest {
	
	@NotBlank
	private String categoryName;
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public String getSearchText(){
		return categoryName.replaceAll("[ ,/&]", "|");		
	}
	
	@Override
	public String toString() {
		return "InsertCategoryRequest [categoryName=" + categoryName + "]";
	}
}
