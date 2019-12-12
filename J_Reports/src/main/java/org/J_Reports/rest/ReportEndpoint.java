//package org.J_Reports.rest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.ejb.Stateless;
//import javax.json.Json;
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.persistence.OptimisticLockException;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
//
//import javax.ws.rs.core.UriBuilder;
//import javax.json.JsonReader;
//import javax.json.JsonObject;
//import javax.json.JsonArray;
//import javax.json.JsonValue;
//import javax.websocket.server.PathParam;
//
//import org.J_Reports.model.Report;
//import org.J_Reports.model.UserGroup;
//import org.json.JSONObject;
//
//import responseobject.ColumnMetadata;
//import responseobject.ReportMetadata;
//
//import utilities.AppDBConnection;
//
//import java.io.StringReader;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
///**
// * 
// */
//@Stateless
//@Path("/")
//public class ReportEndpoint {
//	@PersistenceContext(unitName = "J_Reports-persistence-unit")
//	private EntityManager em;
//
//
//    @POST
//	@Consumes("application/json")
//	@Produces("application/json")
//	@Path("/create")
//	public Response createReport(@PathParam("data") String input) {
//      JsonReader reader = Json.createReader(new StringReader(input));
//      JsonObject reportObj = reader.readObject();
//      reader.close();
//
//      List<ColumnMetadata> columnMetadata = new ArrayList<>();
//      List<Integer> groupList = new ArrayList<>();
//      
//      JsonObject rsmdObj = reportObj.getJsonObject("resultMD");
//      JsonArray cmd = rsmdObj.getJsonArray("columnMetadata");
//      
//      for(int i = 0; i < cmd.size(); i++){
//         String colName, colType, colAlias;
//         JsonObject temp = cmd.getJsonObject(i);
//         colName = temp.getString("colName");
//         colType = temp.getString("colType");
//         colAlias = temp.getString("colAlias");
//         ColumnMetadata tempCMD = new ColumnMetadata(colName, colType, colAlias);
//         columnMetadata.add(tempCMD);
//      }
//      JsonArray groups = reportObj.getJsonArray("groups");
//      for(JsonValue jsonVal : groups){
//            groupList.add(Integer.valueOf(jsonVal.toString()));
//      }
////ResultMD
//      ReportMetadata resultMD = new ReportMetadata(columnMetadata);
//      
//      Report repObj = new Report(reportObj.getString("report_title"), reportObj.getString("report_desc"), reportObj.getString("query_string"), reportObj.getInt("datasource_id"), resultMD);
//   
//
//      Connection con = AppDBConnection.getConnection();
//      PreparedStatement ps = null;
//      PreparedStatement ps2 = null;
//      Statement stmt = null;
//      ResultSet rs = null;
//      int id = 0;
//
//      String insert1 = "INSERT INTO report (Title, Query, Description, Metadata, connection_ID)values ('" +
//      repObj.getReport_title()+"', '"+ repObj.getQuery_string() +"', '"+ repObj.getReport_desc()+ "', '"+ repObj.getResultMD() + "', " + repObj.getDatasource_id() + ");";
//      String insert2 = "INSERT INTO reports (report_ID, usergroup_ID) values (?,?)";
//
//      try{
//         ps = con.prepareStatement (insert1, Statement.RETURN_GENERATED_KEYS);
//         ps.executeUpdate();
//         rs = ps.getGeneratedKeys();
//         if(rs != null && rs.next()){
//            id = rs.getInt(1);
//         }
//         ps2 = con.prepareStatement(insert2);
//         //need to set up a loop body to iterate through all user groups given
//         for (int i = 0; i < groupList.size(); i++) {
//             ps2.setInt(1, id);
//             ps2.setInt(2, groupList.get(i));
//             ps2.execute();
//          }
//         JSONObject successObj = new JSONObject();
//         successObj.put("result", "success");
//         return Response.ok(successObj.toString()).build();
//      }catch(SQLException e){
//         e.printStackTrace();
//         return Response.status(Status.BAD_REQUEST).build();
//      }
//      
//      
//	}
//
//}
