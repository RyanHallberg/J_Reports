//package org.J_Reports.model;
//
//import javax.persistence.Id;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import java.io.Serializable;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "reports")
//public class Reports implements Serializable {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id", updatable = false, nullable = false)
//	private Long id;
//	private static final long serialVersionUID = 1L;
//
//	@Column(name = "report_ID")
//	private int reportID;
//
//	@Column(name = "connection_ID")
//	private int datasourceID;
//
//	@Column(name = "usergroup_ID")
//	private int userGroupID;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		if (!(obj instanceof Reports)) {
//			return false;
//		}
//		Reports other = (Reports) obj;
//		if (id != null) {
//			if (!id.equals(other.id)) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
//
//	public int getReportID() {
//		return reportID;
//	}
//
//	public void setReportID(int reportID) {
//		this.reportID = reportID;
//	}
//
//	public int getDatasourceID() {
//		return datasourceID;
//	}
//
//	public void setDatasourceID(int datasourceID) {
//		this.datasourceID = datasourceID;
//	}
//
//	public int getUserGroupID() {
//		return userGroupID;
//	}
//
//	public void setUserGroupID(int userGroupID) {
//		this.userGroupID = userGroupID;
//	}
//
//	@Override
//	public String toString() {
//		String result = getClass().getSimpleName() + " ";
//		result += "reportID: " + reportID;
//		result += ", datasourceID: " + datasourceID;
//		result += ", userGroupID: " + userGroupID;
//		return result;
//	}
//}