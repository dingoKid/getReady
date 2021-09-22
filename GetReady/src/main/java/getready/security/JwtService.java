package getready.security;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtService {
	
	private static final String AUTH = "auth";
	private static final String USERNAME = "username";

	public String createJwtToken(UserDetails principal) {
		return JWT.create()
				.withClaim(USERNAME, principal.getUsername())
				.withArrayClaim(AUTH, principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
				.withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5)))
				.withIssuer("getready")
				.sign(Algorithm.HMAC256("secret"));
	}
	
	public UserDetails parseJwt(String jwtToken) {
		DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("secret"))
				.withIssuer("getready")
				.build()
				.verify(jwtToken);
		
		return new User(decodedJWT.getClaim(USERNAME).asString(), "dummy", 
				decodedJWT.getClaim(AUTH).asList(String.class)
				.stream()
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
	}
	
}
