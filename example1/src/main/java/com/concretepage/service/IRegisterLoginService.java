package com.concretepage.service;

import java.util.List;
import java.util.Set;


import com.concretepage.entity.CustomerDetails;
import com.concretepage.entity.SignUpStatus;
import com.concretepage.entity.login;

public interface IRegisterLoginService {
     
    
	SignUpStatus register(CustomerDetails cusDetails);
     boolean login(login login);
     

     CustomerDetails getUserByEmailId(String EmailId);
     
	   
}

