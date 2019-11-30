package requestobject;

import utilities.QueryReformatter;

public class ReportTemplate {
	private Long datasource_id;
	private String query_string;
	
	public Long getDatasource_id() {
		return datasource_id;
	}
	public void setDatasource_id(Long datasource_id) {
		this.datasource_id = datasource_id;
	}
	public String getQuery_string() {
		return query_string;
	}
	public void setQuery_string(String query_string) {
		this.query_string = QueryReformatter.reformat(query_string);
	}
	
	
}
