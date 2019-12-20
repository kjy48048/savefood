package com.ward.savefood.member.model;

import javax.validation.constraints.NotBlank;

public class TelegramAuthRequest {
	
	@NotBlank
	private String memberId;
	
	@NotBlank
	private String authKey;
	
	public String getMemberId() {
		return memberId;
	}
	public String getAuthKey() {
		return authKey;
	}
	
	@Override
	public String toString() {
		return "TelegramAuthRequest [memberId=" + memberId + ", authKey=" + authKey + "]";
	}
}
