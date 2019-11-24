
//package org.J_Reports.rest;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.Statement;
//
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Response;
//
//import org.J_Reports.model.Datasource;
//
//import requestobject.ReportTemplate;
//import responseobject.ColumnMetadata;
//import responseobject.ReportMetadata;
//
//@Path("/reports/template")
//public class ReportTemplateEndpoint {
//	@PersistenceContext(unitName = "J_Reports-persistence-unit")
//	private EntityManager em;
//	
//	@POST
//	@Consumes("application/json")
//	@Produces("application/json")
//	public Response createReportTemplate(ReportTemplate reportTemplate) {
//		// get the data source
//		TypedQuery<Datasource> findByIdQuery = em
//				.createQuery(
//						"SELECT DISTINCT d FROM Datasource d WHERE d.id = :entityId ORDER BY d.id",
//						Datasource.class);
//		findByIdQuery.setParameter("entityId", reportTemplate.getDatasourceID());
//		Datasource entity;
//		try {
//			entity = findByIdQuery.getSingleResult();
//		} catch (NoResultException nre) {
//			
//			// return error here
//			entity = null;
//		}
//		
//		// instantiate the database connection
//		Connection con = null;
//		try
//		{
//			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); // load the MS Access driver
//			
//			String url = "jdbc:ucanaccess://" + entity.getPath(); // gets the connection instance
//			
//			con = DriverManager.getConnection(url + entity.getUserName() + entity.getPassword());
//		}
//		catch (Exception e)
//		{
//			// return error here
//			System.out.println("Error: " + e);
//		}
//		
//		// query the database
//		ReportMetadata reportMetadata = null;
//		try
//		{
//			Statement st = con.createStatement();
//			ResultSet rs = st.executeQuery(reportTemplate.getQuery());
//			ResultSetMetaData rsmd = rs.getMetaData();
//			
//			// set the metadata
//			reportMetadata = new ReportMetadata();
//			reportMetadata.setNumColumns(rsmd.getColumnCount());
//			reportMetadata.setTitle(rsmd.getTableName(1));
//			
//			for (int i = 1; i<= reportMetadata.getNumColumns(); i++) {
//				ColumnMetadata columnMetadata = new ColumnMetadata();
//				columnMetadata.setTableColumnName(rsmd.getColumnName(i));
//				columnMetadata.setDataType(rsmd.getColumnTypeName(i));
//				columnMetadata.setDisplayColumnName(rsmd.getColumnName(i));
//				columnMetadata.setModifiable(false);
//				
//				// add the columnMetadata to the ReportMetadata
//				reportMetadata.addColumn(columnMetadata);
//			}
//			
//		} catch(Exception e) {
//			
//		}
//	
//		return Response.ok(reportMetadata).build();
//	}
//}

package org.J_Reports.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.J_Reports.model.Datasource;
import org.J_Reports.model.RunnableReport;

import requestobject.ReportTemplate;
import responseobject.ColumnMetadata;
import responseobject.ReportMetadata;

@Path("/reports/template")
public class ReportTemplateEndpoint {
	@PersistenceContext(unitName = "J_Reports-persistence-unit")
	private EntityManager em;
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createReportTemplate(ReportTemplate reportTemplate) {
		// get the data source
		TypedQuery<Datasource> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT d FROM Datasource d WHERE d.id = :entityId ORDER BY d.id",
						Datasource.class);
		findByIdQuery.setParameter("entityId", reportTemplate.getDatasourceID());
		Datasource entity;
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			
			// return error here
			entity = null;
		}
		
		// instantiate the database connection
		Connection con = null;
		try
		{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); // load the MS Access driver
			
			String url = "jdbc:ucanaccess://" + entity.getPath(); // gets the connection instance
			
			con = DriverManager.getConnection(url + entity.getUserName() + entity.getPassword());
		}
		catch (Exception e)
		{
			// return error here
			System.out.println("Error: " + e);
		}
		
		// query the database
		ReportMetadata reportMetadata = null;
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(reportTemplate.getQuery());
			ResultSetMetaData rsmd = rs.getMetaData();
			
			// set the metadata
			reportMetadata = new ReportMetadata();
			reportMetadata.setNumColumns(rsmd.getColumnCount());
			reportMetadata.setTitle(rsmd.getTableName(1));
			
			for (int i = 1; i<= reportMetadata.getNumColumns(); i++) {
				ColumnMetadata columnMetadata = new ColumnMetadata();
				columnMetadata.setTableColumnName(rsmd.getColumnName(i));
				columnMetadata.setDataType(rsmd.getColumnTypeName(i));
				columnMetadata.setDisplayColumnName(rsmd.getColumnName(i));
				columnMetadata.setModifiable(false);
				
				// add the columnMetadata to the ReportMetadata
				reportMetadata.addColumn(columnMetadata);
			}
			
			// get the records
			
		} catch(Exception e) {
			
		}
	
		return Response.ok(reportMetadata).build();
	}
}

























