package org.J_Reports.model;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRole implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private static final long serialVersionUID = 1L;

	@Column(name = "user_ID")
	private int userID;

	@Column(name = "userType_ID")
	private int userTypeID;

	@Column(name = "usergroup_ID")
	private int userGroupID;

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
		if (!(obj instanceof UserRole)) {
			return false;
		}
		UserRole other = (UserRole) obj;
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

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getUserTypeID() {
		return userTypeID;
	}

	public void setUserTypeID(int usertype_ID) {
		this.userTypeID = usertype_ID;
	}

	public int getUserGroupID() {
		return userGroupID;
	}

	public void setUserGroupID(int usergroup_ID) {
		this.userGroupID = usergroup_ID;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		result += "userID: " + userID;
		result += ", usertype_ID: " + userTypeID;
		result += ", usergroup_ID: " + userGroupID;
		return result;
	}
}