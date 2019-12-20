package com.ward.savefood.member.model;

import javax.validation.constraints.NotBlank;

public class CreateAuthKeyRequest {
	
	@NotBlank
	private String memberId;
	
	public String getMemberId() {
		return memberId;
	}
	
	@Override
	public String toString() {
		return "CreateAuthKeyRequest [memberId=" + memberId + "]";
	}
}
