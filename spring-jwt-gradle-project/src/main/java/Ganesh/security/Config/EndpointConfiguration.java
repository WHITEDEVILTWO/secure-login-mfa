package Ganesh.security.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import Ganesh.security.Filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class EndpointConfiguration {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	 
	
//	@Autowired
//	private  JwtAuthenticationFilter jwtAuthenticationFilter;
//	
//	
//	@Bean
//	public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService);
//		authProvider.setPasswordEncoder(passwordEncoder());
//		return authProvider;
//	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
//		.authorizeHttpRequests(auth -> auth
//			    .requestMatchers("/api/auth/**").permitAll());
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth ->auth
			.requestMatchers("/api/auth/**",
					  "/v3/api-docs/**",
			            "/swagger-ui/**",
			            "/swagger-ui.html",
			            "/swagger-resources/**",
			            "/webjars/**")
			.permitAll()
			.anyRequest().authenticated()
			)
			.sessionManagement(session -> session
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return httpSecurity.build();
	}
	
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
	 
	

	
}

