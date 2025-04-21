
package Ganesh.security.dto;

public class GetUserResponse {
		
		private String username;
		
		private String role;
		
		public String getUsername() {
			return username;
		}
	
		public String getRole() {
			return role;
		}
		
		public GetUserResponse(String username,String role) {
			super();
			this.username = username;
			this.role=role;
		}
		

}
