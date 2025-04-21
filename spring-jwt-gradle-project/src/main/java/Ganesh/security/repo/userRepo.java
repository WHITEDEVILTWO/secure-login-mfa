/**
 * 
 */
package Ganesh.security.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ganesh.security.dto.AddUserRequest;
import Ganesh.security.entity.AppUser;
import java.util.List;


/**
 * 
 */
@Repository
public interface userRepo extends JpaRepository<AppUser, Long> {
	
	Optional<AppUser>  findByUsername(String username);
	Optional<AppUser> findByEmail(String email);
//	AppUser addUser(String username, String password);

}
