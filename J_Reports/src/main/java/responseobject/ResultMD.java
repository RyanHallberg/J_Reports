package responseobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultMD implements Serializable{
	private static final long serialVersionUID = 1L;
	
	List<ColumnMetadata> columnMetadata = new ArrayList<>();
	
	public void addColumn(ColumnMetadata columnMetadata) {
		this.columnMetadata.add(columnMetadata);
	}
	
	public List<ColumnMetadata> getColumnMetadata() {
		return columnMetadata;
	}
	
	public void setColumnMetadata(List<ColumnMetadata> columnMetadata) {
		this.columnMetadata = columnMetadata;
	}
}
