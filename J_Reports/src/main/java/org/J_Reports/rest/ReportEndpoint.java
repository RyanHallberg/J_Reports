package org.J_Reports.rest;

import java.util.List;

import javax.ejb.Stateless;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.J_Reports.model.Report;

/**
 * 
 */
@Stateless
@Path("/reports")
public class ReportEndpoint {
	@PersistenceContext(unitName = "J_Reports-persistence-unit")
	private EntityManager em;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response create(Report entity) {
		em.persist(entity);
		return Response.created(
				UriBuilder.fromResource(ReportEndpoint.class)
						.path(String.valueOf(entity.getReport_id())).build()).build();
	}
	@DELETE
	@Path("/{report_id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response deleteById(@PathParam("report_id") Long report_id) {
		Report entity = em.find(Report.class, report_id);
		
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(entity);
		em.flush();
//		TypedQuery<Report> removeByIdQuery = em
//				.createQuery(
//						"DELETE r FROM Report r WHERE r.report_id = :entityId",
//						Report.class);
//		removeByIdQuery.setParameter("entityId", report_id).executeUpdate();
		//em.remove(entity);
		return Response.ok(entity).build();
	}
//	@DELETE
//	@Path("/{id:[0-9][0-9]*}")
//	public Response deleteById(@PathParam("id") Long id) {
//		Report entity = em.find(Report.class, id);
//		if (entity == null) {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//		em.remove(entity);
//		
//		return Response.noContent().build();
//	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		TypedQuery<Report> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT r FROM Report r WHERE r.report_id = :entityId ORDER BY r.report_id",
						Report.class);
		findByIdQuery.setParameter("entityId", id);
		Report entity;
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
	public List<Report> listAll(
			@QueryParam("start") Integer startPosition,
			@QueryParam("max") Integer maxResult) {
		TypedQuery<Report> findAllQuery = em.createQuery(
				"SELECT DISTINCT r FROM Report r ORDER BY r.report_id",
				Report.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		final List<Report> results = findAllQuery.getResultList();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response update(@PathParam("id") Long id, Report entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(entity.getReport_id())) {
			return Response.status(Status.CONFLICT).entity(entity).build();
		}
		if (em.find(Report.class, id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			entity = em.merge(entity);
			em.flush();
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT)
					.entity(e.getEntity()).build();
		}

		return Response.ok(entity).build();
	}
}
