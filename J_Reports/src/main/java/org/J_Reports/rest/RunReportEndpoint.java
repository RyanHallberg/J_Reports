package org.J_Reports.rest;

import java.sql.Connection;
import java.sql.DriverManager;

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

import requestobject.ReportTemplate;

@Stateless
@Path("/reports/run")
public class RunReportEndpoint {
	@PersistenceContext(unitName = "J_Reports-persistence-unit")
	private EntityManager em;

//	@POST
//	@Consumes("application/json")
//	@Produces("application/json")
//	public Response runReport(ReportTemplate reportTemplate) {
//		// get the report by id
//		Long id = reportTemplate.getDatasource_id();
//		
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
	
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/{id:[0-9][0-9]*}")
	public Response runReport(@PathParam("id") Long id) {
		// get the report by id
		TypedQuery<Report> findReportByIdQuery = em
				.createQuery(
						"SELECT DISTINCT r FROM Report r WHERE r.report_id = :entityId ORDER BY r.report_id",
						Report.class);
		findReportByIdQuery.setParameter("entityId", id);
		Report report;
		try {
			report = findReportByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			report = null;
		}
		if (report == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		// get the datasource
		TypedQuery<Datasource> findDatasourceByIdQuery = em
				.createQuery(
						"SELECT DISTINCT d FROM Datasource d WHERE d.id = :entityId ORDER BY d.id",
						Datasource.class);
		findDatasourceByIdQuery.setParameter("entityId", id);
		Datasource datasource;
		try {
			datasource = findDatasourceByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			datasource = null;
		}
		if (datasource == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		// instantiate the database connection
		Connection con = null;
		try
		{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); // load the MS Access driver
			
			String url = "jdbc:ucanaccess://" + datasource.getConnection_string(); // gets the connection instance
			
			con = DriverManager.getConnection(url + datasource.getUsername() + datasource.getPassword());
		}
		catch (Exception e)
		{
			// return error here
			System.out.println("Error: " + e);
			
		}
		
		// run the query
		return null;
	}
}
