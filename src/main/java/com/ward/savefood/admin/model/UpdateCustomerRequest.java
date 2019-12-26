package com.ward.savefood.admin.model;

import javax.validation.constraints.NotNull;

public class UpdateCustomerRequest {
	
	@NotNull
	private int memberSeq;
	private int memberRole;
	private int memberStatus;
	
	public int getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}
	public int getMemberRoleId() {
		return memberRole;
	}
	public void setMemberRoleId(int memberRoleId) {
		this.memberRole = memberRoleId;
	}
	public int getMemberStatusId() {
		return memberStatus;
	}
	public void setMemberStatusId(int memberStatusId) {
		this.memberStatus = memberStatusId;
	}
	@Override
	public String toString() {
		return "UpdateCustomerRequest [memberSeq=" + memberSeq + ", memberRoleId=" + memberRole + ", memberStatusId="
				+ memberStatus + "]";
	}
	
}
