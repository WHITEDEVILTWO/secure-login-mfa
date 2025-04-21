//package Ganesh.security.controller;
//
//import Ganesh.security.dto.Auth.AuthRequest;
//import Ganesh.security.dto.Auth.AuthResponse;
//import Ganesh.security.security.JwtService;
//import lombok.RequiredArgsConstructor;
//import Ganesh.security.entity.AppUser;
//import Ganesh.security.repo.userRepo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/auth")
//public class AuthController {
//	
//	@Autowired
//    private  AuthenticationManager authenticationManager;
//	@Autowired
//    private  JwtService jwtService;
//	@Autowired
//    private  userRepo userRepo;
//
//    @PostMapping("/login")
//    public AuthResponse login(@RequestBody AuthRequest request) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.getUsername(), request.getPassword()
//                    )
//            );
//
//            AppUser user = userRepo.findByUsername(request.getUsername())
//                    .orElseThrow(() -> new RuntimeException("User not found"));
//
//            String token = jwtService.generateToken(user.getUsername());
//            return new AuthResponse(token);
//
//        } catch (AuthenticationException e) {
//            throw new RuntimeException("Invalid username or password");
//        }
//    }
//}
