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
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;

	@Column(name = "Name", nullable = true)
	private String name;

	@Column(name = "Username", nullable = true)
	private String username;

	@Column(name = "Password", nullable = true)
	private String password;

	@Column(name = "URL")
	private String connection_string;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConnection_string() {
		return connection_string;
	}

	public void setConnection_string(String path) {
		this.connection_string = path;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (name != null && !name.trim().isEmpty())
			result += "name: " + name;
		if (username != null && !username.trim().isEmpty())
			result += ", userName: " + username;
		if (password != null && !password.trim().isEmpty())
			result += ", password: " + password;
		if (connection_string != null && !connection_string.trim().isEmpty())
			result += ", path: " + connection_string;
		return result;
	}
}