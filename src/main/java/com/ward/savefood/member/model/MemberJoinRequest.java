package com.ward.savefood.member.model;

import javax.validation.constraints.NotNull;

public class MemberJoinRequest {
	
	@NotNull
	private String memberId; // 아이디
	
	@NotNull
	private String password; // 비밀번호
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "MemberJoinRequest [memberId=" + memberId + ", password=" + password + "]";
	}
}
