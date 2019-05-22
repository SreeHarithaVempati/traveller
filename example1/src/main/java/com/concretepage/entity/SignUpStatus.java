package com.concretepage.entity;

import org.springframework.stereotype.Component;


@Component
public class SignUpStatus {
	private boolean status;
	private String error="No error";
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "SignupStatus [status=" + status + ", error=" + error + "]";
	}
	public boolean equals(SignUpStatus obj) {
		// TODO Auto-generated method stub
		if( this.status==obj.status)
			return true;
		else
			return false;
	}
	
}