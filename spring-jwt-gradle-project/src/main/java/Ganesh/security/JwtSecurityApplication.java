
package Ganesh.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import Ganesh.security.Filter.JwtAuthenticationFilter;
import Ganesh.security.service.JwtService;

@SpringBootApplication(scanBasePackages = "Ganesh")
//@ComponentScan(basePackageClasses =JwtAuthenticationFilter.class)
public class JwtSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtSecurityApplication.class, args);
        
       
       
    }
}
