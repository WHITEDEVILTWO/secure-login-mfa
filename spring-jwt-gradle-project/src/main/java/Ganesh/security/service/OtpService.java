package Ganesh.security.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private static final int EXPIRE_MINUTES = 1;
    
    private final ConcurrentHashMap<String, OtpEntry> otpStorage = new ConcurrentHashMap<>();

    public String generateOtp(String email) {
        String otp = String.format("%04d", new Random().nextInt(9999));
        otpStorage.put(email, new OtpEntry(otp, LocalDateTime.now().plusMinutes(EXPIRE_MINUTES))); // temporary storage
        return otp;
    }

    private static class OtpEntry {
    	String otp;
    	LocalDateTime expiryTime;
    	
    	OtpEntry(String otp, LocalDateTime expiryTime) {
    		this.otp = otp;
    		this.expiryTime = expiryTime;
    	}
    }
    
    public boolean validateOtp(String email, String eOTP) {
        OtpEntry entry = otpStorage.get(email);
        if (entry == null) return false;
        if (entry.expiryTime.isBefore(LocalDateTime.now())) {
            otpStorage.remove(email); //removing data 
            return false;
        }
        boolean isValid = entry.otp.equals(eOTP);
        if (isValid) otpStorage.remove(email); // One-time use
        return isValid;
    }
   

}
