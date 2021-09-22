package getready.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import getready.dto.LoginDto;

@RestController
public class JwtLoginController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	@PostMapping("/api/login")
	public String login(@RequestBody LoginDto login) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
		
		return jwtService.createJwtToken((UserDetails)authentication.getPrincipal());
	}
}
