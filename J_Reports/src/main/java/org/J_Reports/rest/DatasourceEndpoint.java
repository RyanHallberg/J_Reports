package org.J_Reports.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
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
import org.J_Reports.model.Datasource;

import utilities.TestDBConn;

/**
 * 
 */
@Stateless
@Path("/datasources")
public class DatasourceEndpoint {
	@PersistenceContext(unitName = "J_Reports-persistence-unit")
	private EntityManager em;

	@POST
	@Consumes("application/json")
	public Response create(Datasource entity) {
      
      boolean good = TestDBConn.checkDBConnection(entity);

      if(good){
        em.persist(entity);
        return Response.ok(entity).build();
//		return Response.created(
//				UriBuilder.fromResource(DatasourceEndpoint.class)
//                  .path(String.valueOf(entity.getId())).build()).build();
      }else{
         return Response.status(Status.NOT_FOUND).build();
      }
	}

	@DELETE
	@Transactional
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		Datasource entity = em.find(Datasource.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(entity);
		em.flush();
		return Response.ok(entity).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		TypedQuery<Datasource> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT d FROM Datasource d WHERE d.id = :entityId ORDER BY d.id",
						Datasource.class);
		findByIdQuery.setParameter("entityId", id);
		Datasource entity;
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
	public List<Datasource> listAll(@QueryParam("start") Integer startPosition,
			@QueryParam("max") Integer maxResult) {
		TypedQuery<Datasource> findAllQuery = em.createQuery(
				"SELECT DISTINCT d FROM Datasource d ORDER BY d.id",
				Datasource.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		final List<Datasource> results = findAllQuery.getResultList();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Long id, Datasource entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(entity.getId())) {
			return Response.status(Status.CONFLICT).entity(entity).build();
		}
		if (em.find(Datasource.class, id) == null) {
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
