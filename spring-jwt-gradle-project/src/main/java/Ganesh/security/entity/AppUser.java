package Ganesh.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import static jakarta.persistence.GenerationType.AUTO;

@Entity
public class AppUser {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long Id;
	
	private String username;
	private String password;
	private String role;

	public String email;

	/**
	 * @return the id
	 */
	public long getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		Id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getpassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setpassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
	this.email = email;
	}

	public AppUser(String username, String password, String role,String email ) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.email=email;
	}

//	public AppUser(long id, String username, String password, String role) {
//		super();
//		Id = id;
//		this.username = username;
//		this.password = password;
//		this.role = role;
//	}

	public AppUser() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AppUser [Id=" + Id + ", username=" + username + ", password=" + password + ", role=" + role + ", email="
				+ email + "]";
	}

	
	

}
