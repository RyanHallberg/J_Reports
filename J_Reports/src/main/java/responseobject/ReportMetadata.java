package responseobject;

import java.util.ArrayList;
import java.util.List;

public class ReportMetadata {	
	private String title;
	private List<ColumnMetadata> columnMetadata = new ArrayList<>();
	private int numColumns;
	
	public void addColumn(ColumnMetadata columnMetadata) {
		this.columnMetadata.add(columnMetadata);
	}
	
	public ColumnMetadata getColumn(int index) {
		return this.columnMetadata.get(index);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<ColumnMetadata> getColumnMetadata() {
		return columnMetadata;
	}
	public void setColumnMetadata(List<ColumnMetadata> columnMetadata) {
		this.columnMetadata = columnMetadata;
	}
	public int getNumColumns() {
		return numColumns;
	}
	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}
}
