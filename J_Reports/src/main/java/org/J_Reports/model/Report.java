package org.J_Reports.model;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;

@Entity
@Table(name = "report")
public class Report implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	private static final long serialVersionUID = 1L;

	@Column(name = "Title")
	private String title;

	@Column(name = "Query_original")
	private String originalQuery;

	@Column(name = "Query_reformated")
	private String reformattedQuery;

	@Column(name = "Description")
	private String description;

	@Column(name = "connection_ID")
	private int datasourceID;

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
		if (!(obj instanceof Report)) {
			return false;
		}
		Report other = (Report) obj;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginalQuery() {
		return originalQuery;
	}

	public void setOriginalQuery(String originalQuery) {
		this.originalQuery = originalQuery;
	}

	public String getReformattedQuery() {
		return reformattedQuery;
	}

	public void setReformattedQuery(String reformattedQuery) {
		this.reformattedQuery = reformattedQuery;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDatasourceID() {
		return datasourceID;
	}

	public void setDatasourceID(int datasourceID) {
		this.datasourceID = datasourceID;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (title != null && !title.trim().isEmpty())
			result += "title: " + title;
		if (originalQuery != null && !originalQuery.trim().isEmpty())
			result += ", originalQuery: " + originalQuery;
		if (reformattedQuery != null && !reformattedQuery.trim().isEmpty())
			result += ", reformattedQuery: " + reformattedQuery;
		if (description != null && !description.trim().isEmpty())
			result += ", description: " + description;
		result += ", datasourceID: " + datasourceID;
		return result;
	}
}