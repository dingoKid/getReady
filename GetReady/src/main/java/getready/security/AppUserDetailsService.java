package getready.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import getready.model.AppUser;
import getready.repository.AppUserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = appUserRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username));
		
		return new User(username, user.getPassword(), 
				user.getRoles().stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList()));
	}

}
