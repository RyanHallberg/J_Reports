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

import org.json.JSONArray;
import org.json.JSONObject;

import responseobject.ColumnMetadata;
import responseobject.ReportMetadata;;


@Entity
@Table(name = "report")
public class Report implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	private static final long serialVersionUID = 1L;

	@Column(length = 75, name = "Title")
	private String title;

	@Column(length = 250, name = "Query", nullable = false)
	private String query;

	@Column(length = 250, name = "Description")
	private String description;

	@Column(name = "connection_ID", nullable = false)
	private int datasourceID;
	
  /*  @Lob
   @Column(name = "Metadata")
   private List<JSONObject> resultMD;
   //private ReportMetadata resultMD; */

   
   
   

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

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

/* 	public List<JSONObject> getResultMD() {
		return resultMD;
	}

	public void setResultMD(List<JSONObject> arr) {
		this.resultMD = new ArrayList<>();
	} */

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
		if (query != null && !query.trim().isEmpty())
			result += ", originalQuery: " + query;
		if (description != null && !description.trim().isEmpty())
			result += ", description: " + description;
		result += ", datasourceID: " + datasourceID;
		return result;
	}

   public Report() {
      this.id = 0L;
      this.title = "";
      this.query = "";
      this.description = "";
      this.datasourceID = 0;
   
   }

   public Report(String title, String query, String description, int datasourceID /*,List<JSONObject> resultMD*/) {
    
      this.title = title;
      this.query = query;
      this.description = description;
      this.datasourceID = datasourceID;
      //this.resultMD = new ArrayList<JSONObject> ();
   }



}