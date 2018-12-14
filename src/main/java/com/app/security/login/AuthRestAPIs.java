package com.app.security.login;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
 
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(userRepository.existsByUname(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }
 
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }
        
        Roles userRole=roleRepository.findById(1).get();
        Center center = centerService.find(1);
        // Creating user's account
        User user = new User(center, userRole, "user", "mind", "email", "mob", "username", encoder.encode("password"), null, null);
 
        Set<String> strRoles = signUpRequest.getRole();
        Set<Roles> roles = new HashSet<>();
 
        strRoles.forEach(role -> {
        	switch(role) {
	    		case "ADMIN":
	    			Roles adminRole = roleRepository.findByRole("ADMIN")
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	    			roles.add(adminRole);
	    			
	    			break;
	    		case "USER":
	            	Roles pmRole = roleRepository.findByRole("USER")
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	            	roles.add(pmRole);
	            	
	    			break;
	    		default:
	        		Roles userRole1 = roleRepository.findByRole("USER")
	                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
	        		roles.add(userRole1);        			
        	}
        });
        
        user.setRoles(userRole);
        userRepository.save(user);
 
        return ResponseEntity.ok().body("User registered successfully!");
    }
}