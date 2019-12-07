package org.J_Reports.rest;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.NoResultException;

import javax.persistence.EntityManager;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.J_Reports.model.User;
import org.json.JSONObject;

import requestobject.UserLoginTemplate;
import utilities.PasswordHasher;

@Path("/users/login")
public class UserLoginEndpoint {
   @PersistenceContext(unitName = "J_Reports-persistence-unit")
   private EntityManager em;

   public static final String ALGORITHM = "SHA-256";

   @POST
   @Consumes("application/json")
   @Produces("application/json")
   public Response loginUser(UserLoginTemplate loginTemplate) {
      // get the data source
      TypedQuery<User> findByUsername = em
    		  .createQuery("SELECT u FROM User u WHERE u.userName = :entityUsername",
    				  User.class);
      findByUsername.setParameter("entityUsername", loginTemplate.getUsername());
      User entity;
      try {
         entity = findByUsername.getSingleResult();
      } catch (NoResultException nre) {

         // return error here
         entity = null;
      }

      String loginPassword;
      String saltedPass = loginTemplate.getPassword() + entity.getPasswordSalt();

      loginPassword = PasswordHasher.getCryptoHash(saltedPass, ALGORITHM);

      // compare hash from user login to hash in user db
      if(!(loginPassword.equals(entity.getPassword()))){

         // Password incorrect
         return Response.status(Status.BAD_REQUEST).entity("User not authenticated").type(MediaType.APPLICATION_JSON).build();
      }
      else{
        // Credentials validated
         
         // create JSON body
         JSONObject loginResponse = new JSONObject();
         loginResponse.put("userID", entity.getId());
         loginResponse.put("userType", entity.getUser_type_id());

         // return 200
         return Response.ok(loginResponse.toString()).build();
      }
      
   }

}