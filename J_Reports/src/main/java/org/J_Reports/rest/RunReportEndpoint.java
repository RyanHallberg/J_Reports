package org.J_Reports.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.J_Reports.model.Datasource;
import org.J_Reports.model.Report;
import org.json.JSONObject;

import requestobject.ReportTemplate;
import responseobject.ResultSetConverter;

@Stateless
@Path("/reports/run")
public class RunReportEndpoint {
	
	@PersistenceContext(unitName = "J_Reports-persistence-unit")
	private EntityManager em;
	
	JSONObject master = new JSONObject();
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createReportTemplate(ReportTemplate reportTemplate) {
		// get the data source
		TypedQuery<Datasource> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT d FROM Datasource d WHERE d.id = :entityId ORDER BY d.id",
						Datasource.class);
		findByIdQuery.setParameter("entityId", reportTemplate.getDatasource_id());
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
			
			String url = "jdbc:ucanaccess://" + entity.getConnection_string(); // gets the connection instance
			
			con = DriverManager.getConnection(url + entity.getUsername() + entity.getPassword());
		}
		catch (Exception e)
		{
			// return error here
			System.out.println("Error: " + e);
		}
		
		// query the database
		//ReportMetadata reportMetadata = null;
		try
		{
			Statement st = con.createStatement();
			// check if query was valid
			ResultSet rs = st.executeQuery(reportTemplate.getQuery_string());
			
			master = ResultSetConverter.convert(rs);
	
			
		} catch(Exception e) {
			
		}
	
		return Response.ok(master.toString()).build();
	}
//	@PersistenceContext(unitName = "J_Reports-persistence-unit")
//	private EntityManager em;

//	@POST
//	@Consumes("application/json")
//	@Produces("application/json")
//	public Response runReport(ReportTemplate reportTemplate) {
//		// get the report by id
//		Long id = reportTemplate.getDatasource_id();
		
//		TypedQuery<Report> findReportByIdQuery = em
//				.createQuery(
//						"SELECT DISTINCT r FROM Report r WHERE r.report_id = :entityId ORDER BY r.report_id",
//						Report.class);
//		findReportByIdQuery.setParameter("entityId", id);
//		Report report;
//		try {
//			report = findReportByIdQuery.getSingleResult();
//		} catch (NoResultException nre) {
//			report = null;
//		}
//		if (report == null) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//		
//		// get the datasource
//		TypedQuery<Datasource> findDatasourceByIdQuery = em
//				.createQuery(
//						"SELECT DISTINCT d FROM Datasource d WHERE d.id = :entityId ORDER BY d.id",
//						Datasource.class);
//		findDatasourceByIdQuery.setParameter("entityId", id);
//		Datasource datasource;
//		try {
//			datasource = findDatasourceByIdQuery.getSingleResult();
//		} catch (NoResultException nre) {
//			datasource = null;
//		}
//		if (datasource == null) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//		
//		// instantiate the database connection
//		Connection con = null;
//		try
//		{
//			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); // load the MS Access driver
//			
//			String url = "jdbc:ucanaccess://" + datasource.getConnection_string(); // gets the connection instance
//			
//			con = DriverManager.getConnection(url + datasource.getUsername() + datasource.getPassword());
//		}
//		catch (Exception e)
//		{
//			// return error here
//			System.out.println("Error: " + e);
//			
//		}
//		
//		// run the query
//		return null;
//	}
	
	
//	@POST
//	@Consumes("application/json")
//	@Produces("application/json")
//	@Path("/{id:[0-9][0-9]*}")
//	public Response runReport(@PathParam("id") Long id) {
//		// get the report by id
//		TypedQuery<Report> findReportByIdQuery = em
//				.createQuery(
//						"SELECT DISTINCT r FROM Report r WHERE r.report_id = :entityId ORDER BY r.report_id",
//						Report.class);
//		findReportByIdQuery.setParameter("entityId", id);
//		Report report;
//		try {
//			report = findReportByIdQuery.getSingleResult();
//		} catch (NoResultException nre) {
//			report = null;
//		}
//		if (report == null) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//		
//		// get the datasource
//		TypedQuery<Datasource> findDatasourceByIdQuery = em
//				.createQuery(
//						"SELECT DISTINCT d FROM Datasource d WHERE d.id = :entityId ORDER BY d.id",
//						Datasource.class);
//		findDatasourceByIdQuery.setParameter("entityId", id);
//		Datasource datasource;
//		try {
//			datasource = findDatasourceByIdQuery.getSingleResult();
//		} catch (NoResultException nre) {
//			datasource = null;
//		}
//		if (datasource == null) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//		
//		// instantiate the database connection
//		Connection con = null;
//		try
//		{
//			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); // load the MS Access driver
//			
//			String url = "jdbc:ucanaccess://" + datasource.getConnection_string(); // gets the connection instance
//			
//			con = DriverManager.getConnection(url + datasource.getUsername() + datasource.getPassword());
//		}
//		catch (Exception e)
//		{
//			// return error here
//			System.out.println("Error: " + e);
//			
//		}
//		
//		// run the query
//		return null;
//	}
}
