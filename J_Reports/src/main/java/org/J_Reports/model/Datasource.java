package org.J_Reports.model;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;

@Entity
@Table(name = "db_connection")
public class Datasource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "db_id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "db_name", nullable = true)
	private String name;

	@Column(name = "db_user", nullable = true)
	private String userName;

	@Column(name = "db_pw", nullable = true)
	private String password;

	@Column(name = "db_path")
	private String path;

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
		if (!(obj instanceof Datasource)) {
			return false;
		}
		Datasource other = (Datasource) obj;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		this.password = password;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (name != null && !name.trim().isEmpty())
			result += "name: " + name;
		if (userName != null && !userName.trim().isEmpty())
			result += ", userName: " + userName;
		if (password != null && !password.trim().isEmpty())
			result += ", password: " + password;
		if (path != null && !path.trim().isEmpty())
			result += ", path: " + path;
		return result;
	}
}