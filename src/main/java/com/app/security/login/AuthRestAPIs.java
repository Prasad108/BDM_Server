package com.app.security.login;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Center.Center;
import com.app.Center.CenterService;
import com.app.Role.Roles;
import com.app.Role.RolesRepository;
import com.app.User.User;
import com.app.User.UserRepository;
import com.app.security.jwt.JwtProvider;
import com.app.security.message.JwtResponse;
import com.app.security.message.LoginForm;
import com.app.security.message.SignUpForm;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RolesRepository roleRepository;

	@Autowired
	CenterService centerService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(),
						loginRequest.getPassword()
						)
				);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if(userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<String>("Username is already taken!",
					HttpStatus.BAD_REQUEST);
		}

		if(userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<String>("Email is already in use!",
					HttpStatus.BAD_REQUEST);
		}
		
		Roles userRole=roleRepository.findById(signUpRequest.getRoles()).get();
		Center center = centerService.find(signUpRequest.getCenter());
		// Creating user's account
		User user = new User(center, userRole, signUpRequest.getName(), signUpRequest.getCounceller(), signUpRequest.getEmail(), signUpRequest.getMob(), signUpRequest.getUsername(),  encoder.encode(signUpRequest.getPassword()), null, null);
		userRepository.save(user);

		return ResponseEntity.ok(signUpRequest);
	}
}