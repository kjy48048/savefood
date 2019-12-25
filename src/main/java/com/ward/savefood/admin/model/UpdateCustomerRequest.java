package com.ward.savefood.admin.model;

import javax.validation.constraints.NotNull;

public class UpdateCustomerRequest {
	
	@NotNull
	private int memberSeq;
	private int memberRoleId;
	private int memberStatusId;
	
	public int getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}
	public int getMemberRoleId() {
		return memberRoleId;
	}
	public void setMemberRoleId(int memberRoleId) {
		this.memberRoleId = memberRoleId;
	}
	public int getMemberStatusId() {
		return memberStatusId;
	}
	public void setMemberStatusId(int memberStatusId) {
		this.memberStatusId = memberStatusId;
	}
	@Override
	public String toString() {
		return "UpdateCustomerRequest [memberSeq=" + memberSeq + ", memberRoleId=" + memberRoleId + ", memberStatusId="
				+ memberStatusId + "]";
	}
	
}
