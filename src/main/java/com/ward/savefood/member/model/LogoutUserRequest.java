package com.ward.savefood.member.model;

public class LogoutUserRequest {
	
	//@NotBlank
	private String memberId;
	
	public String getMemberId() {
		return memberId;
	}
	
	@Override
	public String toString() {
		return "LogoutUserRequest [memberId=" + memberId + "]";
	}
}
