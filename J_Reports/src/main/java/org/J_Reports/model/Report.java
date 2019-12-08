package org.J_Reports.model;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import responseobject.ResultMD;

@Entity
@Table(name = "report")
public class Report implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long report_id;
	private static final long serialVersionUID = 1L;

	@Column(name = "Title")
	private String report_title;

	@Column(name = "Query_original")
	private String query_string;

//	@Column(name = "Query_reformatted")
//	private String reformattedQuery;

	@Column(name = "Description")
	private String report_desc;

	@Column(name = "connection_ID")
	private int datasource_id;
	
	@Column(name = "Metadata")
	@Lob
	private ResultMD resultMD;
	


	public ResultMD getResultMD() {
		return resultMD;
	}

	public void setResultMD(ResultMD resultMD) {
		this.resultMD = resultMD;
	}

	public Long getReport_id() {
		return report_id;
	}

	public void setReport_id(Long report_id) {
		this.report_id = report_id;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Report)) {
			return false;
		}
		Report other = (Report) obj;
		if (report_id != null) {
			if (!report_id.equals(other.report_id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((report_id == null) ? 0 : report_id.hashCode());
		return result;
	}

	public String getReport_title() {
		return report_title;
	}

	public void setReport_title(String title) {
		this.report_title = title;
	}

	public String getQuery_string() {
		return query_string;
	}

	public void setQuery_string(String originalQuery) {
		this.query_string = originalQuery;
	}

//	public String getReformattedQuery() {
//		return reformattedQuery;
//	}
//
//	public void setReformattedQuery(String reformattedQuery) {
//		// use the formatted query algorithm here
//		this.reformattedQuery = reformattedQuery;
//	}

	public String getReport_desc() {
		return report_desc;
	}

	public void setReport_desc(String description) {
		this.report_desc = description;
	}

	public int getDatasource_id() {
		return datasource_id;
	}

	public void setDatasource_id(int datasourceID) {
		this.datasource_id = datasourceID;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (report_title != null && !report_title.trim().isEmpty())
			result += "title: " + report_title;
		if (query_string != null && !query_string.trim().isEmpty())
			result += ", originalQuery: " + query_string;
//		if (reformattedQuery != null && !reformattedQuery.trim().isEmpty())
//			result += ", reformattedQuery: " + reformattedQuery;
		if (report_desc != null && !report_desc.trim().isEmpty())
			result += ", description: " + report_desc;
		result += ", datasourceID: " + datasource_id;
		return result;
	}
}