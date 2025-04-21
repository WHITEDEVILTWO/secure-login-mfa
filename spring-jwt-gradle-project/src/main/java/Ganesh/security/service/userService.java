package Ganesh.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Ganesh.security.dto.AddUserRequest;
import Ganesh.security.dto.GetUserResponse;
import Ganesh.security.entity.AppUser;
import Ganesh.security.repo.userRepo;

@Service
public class userService implements UserServiceInterface {
	
	@Autowired
	private userRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Override
	public AppUser getUserByName(String username) {
		return userRepo.findByUsername(username)
				.orElse(null);
		}
	
		
	
	@Override
	public void addUser(String username, String password,String role,String email) {
	    AppUser user = new AppUser();
	    user.setUsername(username);
	    user.setpassword(passwordEncoder.encode(password)); // always encode
	    user.setRole(role);
	    user.setEmail(email);
	    userRepo.save(user);
	    System.err.println(passwordEncoder.encode(password));
	    System.err.println(email);
	    System.err.println("User saved/added");
	    return ;
	}
	@Override
    public List<AppUser> getall() {
        return userRepo.findAll();
    }


}
