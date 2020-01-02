package com.ward.savefood.food.model;


public class SelectFoodRequest {
	
	private String categorySeq;
	private String searchText;

	public String getCategorySeq() {
		return categorySeq;
	}

	public void setCategorySeq(String categorySeq) {
		this.categorySeq = categorySeq;
	}
	
	public String getSearchText() {
		return searchText.replaceAll("[ ,/&]", "|");
	}

	@Override
	public String toString() {
		return "SelectFoodRequest [categorySeq=" + categorySeq + ", searchText=" + searchText + "]";
	}
	
}
