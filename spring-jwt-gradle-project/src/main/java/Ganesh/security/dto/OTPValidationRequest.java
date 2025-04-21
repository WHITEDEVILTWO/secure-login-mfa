package Ganesh.security.dto;

public class OTPValidationRequest {
	
	private String email;
	private String otp;
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
	/**
	 * @return the otp
	 */
	public String getOtp() {
		return otp;
	}
	/**
	 * @param otp the otp to set
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public OTPValidationRequest(String email, String otp) {
		super();
		this.email = email;
		this.otp = otp;
	}
	public OTPValidationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
