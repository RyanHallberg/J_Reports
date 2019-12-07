package responseobject;

import java.io.Serializable;


public class ColumnMetadata implements Serializable {

	private static final long serialVersionUID = 1L;
	//private int colNum;
	private String colName;
	private String colType;
	private String colAlias;
	//private Boolean colMod;
	
/* 	public int getColNum() {
		return colNum;
	}
	public void setColNum(int colNum) {
		this.colNum = colNum;
	} */
	
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
/* 	public Boolean getColMod() {
		return colMod;
	}
	public void setColMod(Boolean colMod) {
		this.colMod = colMod;
	} */
	
}
