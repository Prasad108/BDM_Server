package com.app.security.services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.Center.Center;
import com.app.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
 
public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;
 
	private int id;
 
    private String name;
 
    private String username;
 
    private String email;
    
    private String mob;
    
    private String counceller;
    
    public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getCounceller() {
		return counceller;
	}

	public void setCounceller(String counceller) {
		this.counceller = counceller;
	}

	public Center getCenter() {
		return center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

	private Center center;
 
    @JsonIgnore
    private String password;
 
    private Collection<? extends GrantedAuthority> authorities;
 
//    public UserPrinciple(int id, String name, 
//			    		String username, String email, String password, 
//			    		Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.name = name;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }
// 
    public UserPrinciple(int id, String name, String username, String email, String mob, String counceller,
			Center center, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.mob = mob;
		this.counceller = counceller;
		this.center = center;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add( new SimpleGrantedAuthority(user.getRoles().getRole()));
 
        return new UserPrinciple(
        		 user.getId(),
                 user.getName(),
                 user.getUsername(),
                 user.getEmail(),
                 user.getMob(),
                 user.getCounceller(),
                 user.getCenter(),
                 user.getPwd(),
                 authorities
        );
    }
 
    public int getId() {
        return id;
    }
 
    public String getName() {
        return name;
    }
 
    public String getEmail() {
        return email;
    }
 
    @Override
    public String getUsername() {
        return username;
    }
 
    @Override
    public String getPassword() {
        return password;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}
