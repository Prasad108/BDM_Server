package com.app.security.message;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
 
public class SignUpForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
 
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;
 
    @NotBlank
    @Size(max = 60)
    @Email
    private String email;
    
    private int role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
   
    private int center;
    
    @NotBlank
    private String counceller;
    
    @NotBlank
    @Size(min = 10, max = 11)
    private String mob;
 
    public String getCounceller() {
		return counceller;
	}

	public void setCounceller(String counceller) {
		this.counceller = counceller;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public int getCenter() {
		return center;
	}

	public void setCenter(int center) {
		this.center = center;
	}

	public void setPassword(String password) {
        this.password = password;
    }

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
    
   
}