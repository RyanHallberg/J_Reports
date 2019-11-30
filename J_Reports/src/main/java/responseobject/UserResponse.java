package responseobject;

import org.J_Reports.model.User;

public class UserResponse {
	private Long id;
	private String username;
	private int user_type_id;
	
	public UserResponse(User user) {
		this.id = user.getId();
		this.username = user.getUserName();
		this.user_type_id = user.getUser_type_id();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUser_type_id() {
		return user_type_id;
	}
	public void setUser_type_id(int user_type_id) {
		this.user_type_id = user_type_id;
	}
	
}