package responseobject;

public class ColumnMetadata {
	private String tableColumnName;
	private String dataType;
	private String displayColumnName;
	private Boolean modifiable;
	
	public String getTableColumnName() {
		return tableColumnName;
	}
	public void setTableColumnName(String tableColumnName) {
		this.tableColumnName = tableColumnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDisplayColumnName() {
		return displayColumnName;
	}
	public void setDisplayColumnName(String displayColumnName) {
		this.displayColumnName = displayColumnName;
	}
	public Boolean getModifiable() {
		return modifiable;
	}
	public void setModifiable(Boolean modifiable) {
		this.modifiable = modifiable;
	}
}
