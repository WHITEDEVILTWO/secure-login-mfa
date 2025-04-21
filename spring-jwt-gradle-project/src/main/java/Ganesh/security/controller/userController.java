package Ganesh.security.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.*;

import Ganesh.security.dto.AddUserRequest;
import Ganesh.security.dto.GetUserResponse;
import Ganesh.security.dto.OTPValidationRequest;
import Ganesh.security.dto.Auth.AuthRequest;
import Ganesh.security.dto.Auth.AuthResponse;
import Ganesh.security.entity.AppUser;
import Ganesh.security.repo.userRepo;
import Ganesh.security.service.CustomUserDetailsService;
import Ganesh.security.service.EmailService;
import Ganesh.security.service.JwtService;
import Ganesh.security.service.OtpService;
import Ganesh.security.service.userService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
	@RequestMapping("/api/auth")
	public class userController {

        private final CustomUserDetailsService customUserDetailsService;
		
		@Autowired
        private  userRepo userRepo;	
		@Autowired
		private userService userservice;
		@Autowired
		private  AuthenticationManager authenticationManager;
		
		@Autowired
		private  JwtService jwtService;
		
		@Autowired
		private EmailService emailService;
		
		@Autowired
		private OtpService otpService;
		
		@Autowired
		private JavaMailSender javaMailSender;

    userController(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }
		
		
		@GetMapping
		public String test1(){
			System.out.println("This is Test method");
			return "index";
		}
		
		
		@GetMapping("/test")
		public ResponseEntity<String> test(){
			System.out.print("This is Test method2");
			return new ResponseEntity<String>("App is working", HttpStatus.OK);
		}
		@PostMapping("/adduser")
		public HttpStatus add(@RequestBody AddUserRequest request) {
		    System.err.println("Incoming password: " + request.getPassword());
		    userservice.addUser(request.getUsername(), request.getPassword(), request.getRole(),request.getEmail());
		    return HttpStatus.ACCEPTED;
		}
		
		@GetMapping("/id/{name}")
		public GetUserResponse getUser(@PathVariable("name") String username) {
		    //try {
		        if (username == null || username.trim().isEmpty()) {
		            throw new IllegalArgumentException("Username must not be null or empty");
		        }

		        AppUser user = userservice.getUserByName(username);
		        if (user == null) {
		            System.err.println("User not Found");
		            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		        GetUserResponse requesteduser=new GetUserResponse(user.getUsername(), user.getRole());

		        return requesteduser;

		//    } 
		//catch (IllegalArgumentException e) {
		      //  System.err.println("IllegalArgumentException: " + e.getMessage());
		  //  } catch (Exception e) {
		    //    System.err.println("Unexpected error: " + e.getMessage());
		        //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    //}
		}

		@GetMapping("/all")
		public List<GetUserResponse> getAll() { 
			List<AppUser> user =userservice.getall();
			
			List<GetUserResponse> list=new ArrayList<>();
			for(AppUser u:user) {
				
				GetUserResponse resp=new GetUserResponse(u.getUsername(), u.getRole());
				list.add(resp);
			}
			return list;
		}

		//OTP GENERATION POST LOGIN
	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            request.getUsername(), request.getPassword()
	                    )
	            );

	            AppUser user = userRepo.findByUsername(request.getUsername())
	                    .orElseThrow(() -> new RuntimeException("User not found"));
	            
	            String gotp=otpService.generateOtp(user.getEmail());
	            
	            System.err.println(user.getEmail());
	            emailService.sendOtp(user.getEmail(), gotp);
	            
	            //validate otp
	            

//	            String token = jwtService.generateToken(user.getUsername());
//	            AuthResponse response=new AuthResponse(token);
//	            
//	            response.setAccesstoken(token);
//	            
//	            JwtService test=new JwtService();
//	            String Secret_key =test.toString();
//	            System.err.println("Secret key: "+Secret_key);
	            return ResponseEntity.ok("OTP sent to your email successfully. please verify");

	        } catch (AuthenticationException e) {
	            return ResponseEntity.badRequest().body(null);
	        }
	    }
	    
	   //OTP VAILIDATION AND TOKEN GENERATION
	    @PostMapping("/validate-otp")
	    public ResponseEntity<?> OTPValidation(@RequestBody OTPValidationRequest OTPVR){
	    	
	    	boolean isValid=otpService.validateOtp(OTPVR.getEmail(), OTPVR.getOtp());
	    	if(isValid) {
	    	AppUser user = userRepo.findByEmail(OTPVR.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
	    	
	    	//Notifying user
	    	SimpleMailMessage alertuser=new SimpleMailMessage();
	    	alertuser.setTo(OTPVR.getEmail());
	    	alertuser.setSubject("OTP Verification Status");
	    	alertuser.setText("OTP verified successfully");
	    	javaMailSender.send(alertuser);
	    	
	    	
	    	  String token = jwtService.generateToken(user.getUsername());
	          AuthResponse response=new AuthResponse();
	            
	            response.setAccesstoken(token);
	            response.setUsername(user.getUsername());
	            
//	            AuthResponse response = AuthResponse.builder()
//	                    .token(token)
//	                    .username(user.getUsername())
//	                    .build();
	            
	            JwtService test=new JwtService();
	            String Secret_key =test.toString();
	            System.err.println("Secret key: "+Secret_key);
	            
	            return ResponseEntity.ok(response);
	    	}
	    	else {
	    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP/OTP expired"); 
	    	}
	    	
	    }
	    
	    

}
