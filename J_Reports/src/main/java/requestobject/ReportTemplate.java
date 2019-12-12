package requestobject;

import utilities.QueryReformatter;

public class ReportTemplate {
	private Long datasource_id;
	private String query_string;
	
	public Long getDatasource_id() {
		return datasource_id;
	}
	public void setDatasource_id(Long datasourceID) {
		this.datasource_id = datasourceID;
	}
	public String getQuery_string() {
		return query_string;
	}
	public void setQuery_string(String query) {
		this.query_string = QueryReformatter.reformat(query);
	}
	
}
