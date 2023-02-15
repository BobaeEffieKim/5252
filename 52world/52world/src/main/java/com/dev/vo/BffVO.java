package com.dev.vo;

public class BffVO {
	
	private int id;
	private String reqId;
	private String reqName;
	private String applyId;
	private String applyName;
	private String msg;
	
	private String realName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getReqName() {
		return reqName;
	}
	public void setReqName(String reqName) {
		this.reqName = reqName;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getApplyName() {
		return applyName;
	}
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	@Override
	public String toString() {
		return "BffVO [id=" + id + ", reqId=" + reqId + ", reqName=" + reqName + ", applyId=" + applyId + ", applyName="
				+ applyName + ", msg=" + msg + ", realName=" + realName + "]";
	}

	
	
	
	
	
}
