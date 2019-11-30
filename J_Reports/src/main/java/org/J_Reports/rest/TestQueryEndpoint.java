package org.J_Reports.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import org.json.JSONObject;

import requestobject.ReportTemplate;
import responseobject.ResultSetConverter;

@Path("/reports/template")
public class TestQueryEndpoint {
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
			ResultSet rs = st.executeQuery(reportTemplate.getQuery());
			
			master = ResultSetConverter.convert(rs);
	
			
		} catch(Exception e) {
			
		}
	
		return Response.ok(master.toString()).build();
	}
}
