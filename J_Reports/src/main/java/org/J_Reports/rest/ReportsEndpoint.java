package org.J_Reports.rest;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import org.J_Reports.model.Reports;
import javax.websocket.server.PathParam;

import utilities.AppDBConnection;

/**
 * 
 */
@Stateless
@Path("/reports")
public class ReportsEndpoint {
	@PersistenceContext(unitName = "J_Reports-persistence-unit")
   private EntityManager em;
   
   @POST
	@Consumes("application/json")
	public Response create(@PathParam("input") String input) {
      JsonReader reader = Json.createReader(new StringReader(input));
      JsonObject reportsObj = reader.readObject();
      reader.close();

      int reportID = reportsObj.getInt("reportID");
      int usergroupID = reportsObj.getInt("userGroupID");
      
      Connection con = AppDBConnection.getConnection();
      Statement stmt = null;

      String query = "INSERT INTO reports values (" +reportID+", "+ usergroupID + ");";
      
      try{
         stmt = con.createStatement();
         stmt.executeUpdate(query);
         return Response.ok().build();
      }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
         return Response.status(Status.BAD_REQUEST).build();
      }
   }

/* 	@POST
	@Consumes("application/json")
	public Response create(Reports entity) {
		em.persist(entity);
		return Response.created(
				UriBuilder.fromResource(ReportsEndpoint.class)
						.path(String.valueOf(entity.getId())).build()).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		Reports entity = em.find(Reports.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(entity);
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		TypedQuery<Reports> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT r FROM Reports r WHERE r.id = :entityId ORDER BY r.id",
						Reports.class);
		findByIdQuery.setParameter("entityId", id);
		Reports entity;
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			entity = null;
		}
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(entity).build();
	}

	@GET
	@Produces("application/json")
	public List<Reports> listAll(@QueryParam("start") Integer startPosition,
			@QueryParam("max") Integer maxResult) {
		TypedQuery<Reports> findAllQuery = em
				.createQuery("SELECT DISTINCT r FROM Reports r ORDER BY r.id",
						Reports.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		final List<Reports> results = findAllQuery.getResultList();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Long id, Reports entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(entity.getId())) {
			return Response.status(Status.CONFLICT).entity(entity).build();
		}
		if (em.find(Reports.class, id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			entity = em.merge(entity);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT)
					.entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	} */
}
