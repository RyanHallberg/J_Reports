package org.J_Reports.rest;

import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.print.attribute.standard.Media;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.J_Reports.model.Report;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.*;

@Stateless
@Path("/save-report")
public class ReportEndpoint {
   @PersistenceContext(unitName = "J_Reports-persistence-unit")
   private EntityManager em;

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public Response readJSON(@PathParam("title") String key,
                            @PathParam("description") String description,
                            @PathParam("query") String query,
                            @PathParam("datasourceID") int datasourceID/*,               
                            @PathParam("resultMD") List<JSONObject> resultMD
                                                            */ ) {

      // Report report = new Report(title, description, query, datasourceID,
      // resultMD);
     /*  JSONObject jsonObject = new JSONObject(key);
      List<String> list = new ArrayList<>();
      JSONArray ja = jsonObject.getJSONArray("resultMD");

      String alias = "";
      //JSONArray ja = key.getJSONArray("data\\resultMD");
      // JSONTokener jt = new JSONTokener();
      for (int i = 0; i < ja.length(); i++) {

         JSONObject jo = ja.getJSONObject(i);
         
         alias += jo.getString("colAlias");
      } */

      Report reportDetails = new Report(key, description, description, datasourceID);

      return Response.ok(reportDetails).build();

   }
   // https://www.programcreek.com/java-api-examples/?class=org.json.JSONObject&method=getString

}
