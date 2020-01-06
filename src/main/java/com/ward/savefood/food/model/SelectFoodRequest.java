package com.ward.savefood.food.model;


public class SelectFoodRequest {
	
	private int categorySeq;
	private int foodSeq;
	private int storageCode;
	private String searchText;

	public int getCategorySeq() {
		return categorySeq;
	}

	public void setCategorySeq(int categorySeq) {
		this.categorySeq = categorySeq;
	}
	
	public int getFoodSeq() {
		return foodSeq;
	}

	public void setFoodSeq(int foodSeq) {
		this.foodSeq = foodSeq;
	}

	public int getStorageCode() {
		return storageCode;
	}

	public void setStorageCode(int storageCode) {
		this.storageCode = storageCode;
	}

	public String getSearchText() {
		return searchText.replaceAll("[ ,/&]", "|");
	}

	@Override
	public String toString() {
		return "SelectFoodRequest [categorySeq=" + categorySeq + ", foodSeq=" + foodSeq + ", storageCode=" + storageCode
				+ ", searchText=" + searchText + "]";
	}
}
