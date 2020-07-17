package com.group14.app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.group14.app.models.AppUser;
import com.group14.app.models.UserPrincipal;
import com.group14.app.repositories.IAppUserRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

	private IAppUserRepository userRepository;

	private static final Logger LOG = LoggerFactory.getLogger(UserPrincipalDetailsService.class);

	public UserPrincipalDetailsService(IAppUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AppUser user = null;
		try {
			user = this.userRepository.findByUserName(username);
		} catch (Exception e) {
			LOG.error("An error occurred while trying to find the user in DB");
			e.printStackTrace();
		}
		if (user == null) {
			throw new UsernameNotFoundException("Invalid Credentials");
		}
		UserPrincipal userPrincipal = new UserPrincipal(user);
		return userPrincipal;
	}

}
