package responseobject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetConverter {
    public static JSONObject convert(ResultSet rs) throws SQLException, JSONException
    {
        JSONObject master = new JSONObject();
        JSONObject obj1 = new JSONObject();
        JSONArray resultMD = new JSONArray();
        JSONArray resultSet = new JSONArray();
        ResultSetMetaData rsmd = rs.getMetaData();

        int columnCnt = rsmd.getColumnCount();
        for(int i =1; i <= columnCnt; i++){
            JSONObject obj2 = new JSONObject();
            String colName = rsmd.getColumnName(i);
            String colType = rsmd.getColumnTypeName(i);
            obj2.put("colNum", i);
            obj2.put("colName", colName);
            obj2.put("colType", colType);
            obj2.put("colMod", false);
            obj2.put("colAlias","null");
            resultMD.put(obj2);
        }

        obj1.put("resultMD", resultMD);
        while(rs.next()){
            JSONObject obj3 = new JSONObject();
            for(int i = 1; i <= columnCnt; i++){
                String column_name = rsmd.getColumnName(i);
                obj3.put(column_name, rs.getString(i));
            }
            resultSet.put(obj3);
        }
        obj1.put("resultSet",resultSet);
        master.put("data",obj1);
        return master;
    }

}
