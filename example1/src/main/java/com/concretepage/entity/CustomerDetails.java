package com.concretepage.entity;
import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="CustomerDetails")
public class CustomerDetails implements Serializable{
	
 private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		
		@Column(name="customerId")
	    private long customerId; 
		@NotBlank(message="User name should not be empty")
		@Column(name="customerName")
	    private String customerName;
		@NotBlank(message="Mail Id should not be empty")
		@Email(message="Email should be valid")
		@Column(name="mailId")	
		private String mailId;
		@NotBlank(message="Password should not be empty")
		@Size(min=6,message="password should be greater than 6 characters")
		@Column(name="password")	
		private String password;
        @NotNull
		int roles;
		public int getRoles() {
			return roles;
		}

		public void setRoles(int roles) {
			this.roles = roles;
		}

		public CustomerDetails(){
			
		}
		
		public CustomerDetails(String customerName, String mailId, 
				String password) {
			super();
			this.customerName = customerName;
			this.mailId = mailId;
			
			this.password = password;
			
		}
		public long getCustomerId() {
			return customerId;
		}
		public void setCustomerId(long customerId) {
			this.customerId = customerId;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public String getMailId() {
			return mailId;
		}
		public void setMailId(String mailId) {
			this.mailId = mailId;
		}
		
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		
		@Override
		public String toString() {
			return "CustomerDetails [customerId=" + customerId + ", customerName=" + customerName + ", mailId=" + mailId
					 + ", password=" + password+ "]";
		}
		}
	

