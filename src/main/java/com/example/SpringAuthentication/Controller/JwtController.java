package com.example.SpringAuthentication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringAuthentication.Helper.JwtUtil;
import com.example.SpringAuthentication.Model.JwtRequest;
import com.example.SpringAuthentication.Model.JwtResponse;
import com.example.SpringAuthentication.Service.CustomUserDetailsService;

@RestController
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailsService customerUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
System.out.println(jwtRequest);
try {
this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));	
} catch (UsernameNotFoundException e) {
	// TODO: handle exception
	e.printStackTrace();
	throw new Exception("bad credentials");
}
UserDetails userDetails=this.customerUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
	String token=this.jwtUtil.generateToken(userDetails);
	System.out.println("Jwt"+token);
	return ResponseEntity.ok(new JwtResponse(token));
	}
}
