package responseobject;

import java.io.Serializable;

public class ColumnMetadata implements Serializable {

	private static final long serialVersionUID = 1L;
	private String colName;
	private String colType;
    private String colAlias;
   
   

	public ColumnMetadata() {
		
	}
	
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getColType() {
		return colType;
	}
	public void setColType(String colType) {
		this.colType = colType;
	}
	public String getColAlias() {
		return colAlias;
	}
	public void setColAlias(String colAlias) {
		this.colAlias = colAlias;
	}

   public ColumnMetadata(String colName, String colType, String colAlias) {
      this.colName = colName;
      this.colType = colType;
      this.colAlias = colAlias;
   }
}