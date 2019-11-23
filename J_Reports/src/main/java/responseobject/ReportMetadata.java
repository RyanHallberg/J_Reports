package responseobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "report_metadata")
public class ReportMetadata implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	
	private String title;
	private List<ColumnMetadata> columnMetadata = new ArrayList<>();
	private int numColumns;
	private int datasourceID;
	
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

	public int getDatasourceID() {
		return datasourceID;
	}

	public void setDatasourceID(int datasourceID) {
		this.datasourceID = datasourceID;
	}
}
