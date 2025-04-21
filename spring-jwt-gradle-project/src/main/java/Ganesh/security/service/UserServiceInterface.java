package Ganesh.security.service;
import java.util.List;

import Ganesh.security.dto.AddUserRequest;
import Ganesh.security.dto.GetUserResponse;
//
import Ganesh.security.entity.AppUser;
//
public interface UserServiceInterface {
	AppUser getUserByName(String username);
	void addUser(String username, String password,String role,String email);
	List<AppUser> getall();
}
