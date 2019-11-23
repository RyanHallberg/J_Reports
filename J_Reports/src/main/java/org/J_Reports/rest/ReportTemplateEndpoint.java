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

import org.J_Reports.model.Datasource;

import requestobject.ReportTemplateRequest;

@Path("/reports/template")
public class ReportTemplateEndpoint {
	@PersistenceContext(unitName = "J_Reports-persistence-unit")
	private EntityManager em;
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createReportTemplate(ReportTemplateRequest reportTemplateRequest) {
		// get the data source
		TypedQuery<Datasource> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT d FROM Datasource d WHERE d.id = :entityId ORDER BY d.id",
						Datasource.class);
		findByIdQuery.setParameter("entityId", reportTemplateRequest.getDatasourceID());
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
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(reportTemplateRequest.getQuery());
			ResultSetMetaData rsmd = rs.getMetaData();
			
			// set the metadata
			
			
			
		} catch(Exception e) {
			
		}
	
		return Response.ok(entity).build();
	}
}
