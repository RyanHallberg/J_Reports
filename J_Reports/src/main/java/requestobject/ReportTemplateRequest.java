package requestobject;

public class ReportTemplateRequest {

	private Long datasourceID;
	private String query;
	
	public Long getDatasourceID() {
		return datasourceID;
	}
	public void setDatasourceID(Long datasourceID) {
		this.datasourceID = datasourceID;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	
}
