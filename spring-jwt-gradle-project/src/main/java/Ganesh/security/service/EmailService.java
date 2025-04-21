package Ganesh.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailsender;
	
	public String sendOtp(String toMail,String OTP) {
		
		SimpleMailMessage sms=new SimpleMailMessage();
		sms.setTo(toMail);
		sms.setSubject("OTP");
		sms.setText("Dear user,\n\nYour OTP is: " + OTP + "\n\nValid for 1 minute(s).\n\nThanks,\nSecurity Team");
		mailsender.send(sms);
		
		return "Otp sent to email ......";
	}
	
//	public boolean validateOTP(String gOTP,String eOTP) {
//		
//		return gOTP.equals(eOTP);
//			
//	}

}
