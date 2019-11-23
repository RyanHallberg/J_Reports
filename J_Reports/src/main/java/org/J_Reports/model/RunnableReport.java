package org.J_Reports.model;

import javax.persistence.Id;

import responseobject.ReportMetadata;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class RunnableReport extends ReportMetadata implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	private static final long serialVersionUID = 1L;

	@Column
	private Long userRoleID;

	@Column
	private Long datasourceID;

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
		if (!(obj instanceof RunnableReport)) {
			return false;
		}
		RunnableReport other = (RunnableReport) obj;
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

	public Long getUserRoleID() {
		return userRoleID;
	}

	public void setUserRoleID(Long userRoleID) {
		this.userRoleID = userRoleID;
	}

	public Long getDatasourceID() {
		return datasourceID;
	}

	public void setDatasourceID(Long datasourceID) {
		this.datasourceID = datasourceID;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (userRoleID != null)
			result += "userRoleID: " + userRoleID;
		if (datasourceID != null)
			result += ", datasourceID: " + datasourceID;
		return result;
	}
}