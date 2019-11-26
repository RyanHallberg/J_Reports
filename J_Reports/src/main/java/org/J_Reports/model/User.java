package org.J_Reports.model;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;

import utilities.PasswordHasher;


@Entity
@Table(name = "user")
public class User implements Serializable {

   public static final String ALGORITHM = "SHA-256";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	private static final long serialVersionUID = 1L;

	@Column(name = "Username", nullable = false)
	private String userName;

	@Column(name = "Password", nullable = false)
	private String password;

	@Column(name = "Password_salt")
	private String passwordSalt;

	@Column(name = "Email")
	private String email;

	@Column(name = "usertype_ID")
	private int userType;
	

	@Column
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
      setPasswordSalt();
      // use the password hash algorithm here
      password += getPasswordSalt();
      this.password = PasswordHasher.getCryptoHash(password, ALGORITHM);
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt() {
      // set the password salt here
      String passwordSalt;
      passwordSalt = PasswordHasher.createSalt();
	  this.passwordSalt = passwordSalt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (userName != null && !userName.trim().isEmpty())
			result += "userName: " + userName;
		if (password != null && !password.trim().isEmpty())
			result += ", password: " + password;
		if (passwordSalt != null && !passwordSalt.trim().isEmpty())
			result += ", passwordSalt: " + passwordSalt;
		if (email != null && !email.trim().isEmpty())
			result += ", email: " + email;
		result += ", userType: " + userType;
		return result;
	}
	

}