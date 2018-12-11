package org.shell.config.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.shell.services.Services;
import org.shell.user.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private static final Logger log = Logger.getLogger(CustomUserDetailsService.class);
	@Autowired
	private Services services;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.info("loadUserByUsername email : " + email);
		
		email = email.toLowerCase();
		
		BaseUser baseUser;
		try
		{
			baseUser = services.getHome().getBaseUserDao().getByEmail(email);
		}
		catch (Exception e)
		{
			log.error("Error finding User: " + email + " not found");
			throw new UsernameNotFoundException("Error finding User: " + email);
		}
		
		log.info("User : " + baseUser.getEmail() + " found with role :" + baseUser.getRole());
		
		Collection<GrantedAuthority> authorities = getAuthorities(baseUser);
		
		User user = new User(baseUser.getEmail(), baseUser.getPassword(), baseUser.isEnabled(), true, true, true, authorities);
		
		log.info("Using User : " + user.getUsername() + " with authorities :" + authorities);
		return user;
	
	}
	
	private Collection<GrantedAuthority> getAuthorities(BaseUser baseUser) {
		// Create a list of grants for this user
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

		authList.add(new SimpleGrantedAuthority(baseUser.getRole()));

		return authList;
	}
	
}
