package responseobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ReportMetadata implements Serializable {	

	private static final long serialVersionUID = 1L;
	private List<ColumnMetadata> columnMetadata = new ArrayList<>();

	
	public ReportMetadata() {
		
	}

	public void addColumn(ColumnMetadata columnMetadata) {
		this.columnMetadata.add(columnMetadata);
	}
	
	public ColumnMetadata getColumn(int index) {
		return this.columnMetadata.get(index);
	}
	

	public List<ColumnMetadata> getColumnMetadata() {
		return columnMetadata;
	}
	public void setColumnMetadata(List<ColumnMetadata> columnMetadata) {
		//
		
		this.columnMetadata = columnMetadata;
	}

   public ReportMetadata(List<ColumnMetadata> columnMetadata) {
      this.columnMetadata = columnMetadata;
   }

}