package org.J_Reports.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private String username;

	@Column(name = "Password", nullable = false)
	private String password;

	@Column(name = "Password_salt")
	private String passwordSalt;

	@Column(name = "Email")
	private String email;

	@Column(name = "usertype_ID")
	private int user_type_id;
	
//	@OneToMany
//	@JoinTable (
//		name = "usergroup",
//		joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "ID")},
//		inverseJoinColumns = {@JoinColumn(name = "usergroup_id", referencedColumnName = "ID", unique = true)}
//	)
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	//@JoinColumn(name = "user_id")
	private List<UserGroup> user_groups = new ArrayList<>();
	

	public void addUser_groups(UserGroup userGroup) {
		user_groups.add(userGroup);
		userGroup.setUser(this);
	}
	
	public void removeUser_groups(UserGroup userGroup) {
		user_groups.remove(userGroup);
		userGroup.setUser(null);
	}
	
	public List<UserGroup> getUser_groups() {
		return user_groups;
	}

	public void setUser_groups(List<UserGroup> user_groups) {
		this.user_groups = user_groups;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (password != null && !password.trim().isEmpty())
			result += ", password: " + password;
		if (passwordSalt != null && !passwordSalt.trim().isEmpty())
			result += ", passwordSalt: " + passwordSalt;
		if (email != null && !email.trim().isEmpty())
			result += ", email: " + email;
		return result;
	}

	public int getUser_type_id() {
		return user_type_id;
	}

	public void setUser_type_id(int user_type_id) {
		this.user_type_id = user_type_id;
	}
	

}