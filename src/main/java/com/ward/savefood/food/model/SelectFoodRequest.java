package com.ward.savefood.food.model;


public class SelectFoodRequest {
	
	private int categorySeq;
	private String searchText;

	public int getCategorySeq() {
		return categorySeq;
	}

	public void setCategorySeq(int categorySeq) {
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
