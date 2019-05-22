package com.concretepage.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.concretepage.entity.CustomerDetails;
import com.concretepage.entity.SignUpStatus;
import com.concretepage.entity.login;
import com.concretepage.service.RegisterLoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.concretepage.entity.JwtResponse;
import com.concretepage.entity.Role;
import com.concretepage.security.JwtProvider;

@RestController
@RequestMapping("api/auth")
@CrossOrigin("http://localhost:4200")
public class LoginRegisterController extends ExceptionController{
	@Autowired
	private RegisterLoginService busService;
	@Autowired
	private SignUpStatus signupStatus;

@Autowired AuthenticationManager authenticationManager;

@Autowired JwtProvider jwtProvider;

@Autowired PasswordEncoder passwordEncoder;
	
	@PostMapping("/signup")
	public ResponseEntity<SignUpStatus> register(@RequestBody CustomerDetails cusDetails) {
		cusDetails.setPassword(passwordEncoder.encode(cusDetails.getPassword()));
		System.out.println(cusDetails.getPassword());
		SignUpStatus flag = busService.register(cusDetails);

        if (flag.isStatus() == false) {
       
        	return new ResponseEntity<SignUpStatus>(flag,HttpStatus.CONFLICT);
        }
        
        else
        return new ResponseEntity<SignUpStatus>(flag,HttpStatus.OK);
        
	}
	
	@PutMapping("login")
	public ResponseEntity<?> login(@RequestBody login login) {
       
        
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getMailId(),login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken=jwtProvider.generateToken(authentication);
		UserDetails userDetails=(UserDetails)authentication.getPrincipal();
		return ResponseEntity.ok(new JwtResponse(jwtToken, userDetails.getUsername(), userDetails.getAuthorities()));
	}
	
	
	@GetMapping("cusDetails/{EmailId}")
	public ResponseEntity<CustomerDetails> getUserByEmail(@PathVariable("EmailId") String EmailId) {
		CustomerDetails cusDetails = busService.getUserByEmailId(EmailId);
		return new ResponseEntity<CustomerDetails>(cusDetails, HttpStatus.OK);
	}
	
	
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	