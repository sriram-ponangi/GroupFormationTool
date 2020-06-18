package com.group14.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.group14.app.models.AppUser;
import com.group14.app.models.UserPrincipal;
import com.group14.app.repositories.AppUserRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

	@Autowired
	private AppUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AppUser user = this.userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid Credentials");
		}
		UserPrincipal userPrincipal = new UserPrincipal(user);
		return userPrincipal;
	}

}
