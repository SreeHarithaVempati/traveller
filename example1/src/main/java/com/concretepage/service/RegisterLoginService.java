package com.concretepage.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.concretepage.dao.RegisterDao;
import com.concretepage.entity.CustomerDetails;
import com.concretepage.entity.SignUpStatus;
import com.concretepage.entity.login;


import com.concretepage.repository.CusRepository;
import com.concretepage.entity.Role;
import com.concretepage.entity.CustomerDetails;
import com.concretepage.repository.RoleRepository;




@Service
public class RegisterLoginService implements UserDetailsService {
	@Autowired
	private CusRepository cusRepository;
	@Autowired
	private RegisterDao registerDao;
    boolean status;
    
	@Autowired RoleRepository roleRepo;

	public CustomerDetails getUserByEmailId(String EmailId) {
		CustomerDetails obj = cusRepository.findByMailId(EmailId);
		return obj;
	}	
	
	public synchronized SignUpStatus register(CustomerDetails cusDetails){
	   boolean exists= cusRepository.existsByMailId(cusDetails.getMailId());
	   
	   
	   SignUpStatus signupStatus = new SignUpStatus();
       if (exists==true) {
    	  
    	   signupStatus.setStatus(false);
    	   signupStatus.setError("Email already exists!");
    	   
    	   return signupStatus;
    	
       } else {
    	   
    	    signupStatus.setStatus(true);
    		  
    	   cusRepository.save(cusDetails);
    	   
    	   return signupStatus;
       }
       
	}
	
	public  boolean login(login login){
		List<CustomerDetails> list = cusRepository.findByMailIdAndPassword(login.getMailId(), login.getPassword()); 	
	       if (list.size() > 0) {
	    	   return true;
	       } else {
	    	  
	    	   return false;
	       }
		}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
			CustomerDetails users=cusRepository.findByMailId(userName);
			Role roles=roleRepo.findById(users.getRoles());
			List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(roles.getRoleName()));
			User user=new User(users.getCustomerName(),users.getPassword(),authorities);
			return user;
	}
	
}
