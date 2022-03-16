package com.example.SpringAuthentication.Service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if(userName.equals("Kartik"))
		{
			return new User("Kartik","12345",new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("usert not found");
		}
	
	}

}
