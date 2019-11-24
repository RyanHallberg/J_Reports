package utilities;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryReformatter {

   public static String reformat(String query) {

      boolean containsCurlys = QueryReformatter.findModToken(query);

      if (!containsCurlys) {

         // sql string is fine and return sql as is
         return query;

      } else {
         // sql string contains modifications and must be modified
         String queryRefmt = "";
         int j = 0;
         String[] querySplit = query.split(" ");

         ArrayList<Integer> positions = new ArrayList<>(QueryReformatter.getPos(query));
         
         // need to return an error response indicating empty set of curly braces and no value given
         if (positions.size() == 0) {
            return "Error: Modification Token not formatted correctly - Must fit format {value;value type;value name}";
         } else {
            for (int i = 0; i < querySplit.length; i++) {

               if (i == positions.get(j)) {
                  queryRefmt += QueryReformatter.getValues(querySplit[i], j);
                  j++;
               } else {
                  queryRefmt += (querySplit[i]);
               }
               if (i != (querySplit.length - 1))
                  queryRefmt += " ";
            }
         }
         return queryRefmt;
      }
   }

   private static Pattern pattern1 = Pattern.compile("\\{(.*?)\\}");

   /**
    * method detects if query contains token "{a:b:c}"
    * 
    * @param input sql query
    * @return true or false
    */
   public static boolean findModToken(String input) {

      Matcher m = pattern1.matcher(input);
      return m.find();
   }

   /**
    * Method finds postions in query string where mod's exist
    * 
    * @param input
    * @return
    */
   public static ArrayList<Integer> getPos(String input) {

      ArrayList<Integer> positions = new ArrayList<>();
      String words[] = input.split(" ");
      boolean found;
      int i = 0;

      // get positions in string of modifiable
      while (i < words.length) {
         found = Pattern.matches("\\{.*?\\}", words[i]);
         if (found) {
            positions.add(i);
         }
         i++;
      }
      return positions;
   }

   /**
    * Method takes sql tokens and returns substring that represents actual query
    * value
    * 
    * @param input
    * @param pos
    * @return
    */
   public static String getValues(String input, int pos) {
      String temp = "";
      Matcher m = pattern1.matcher(input);
      while (m.find()) {
         temp = m.group(1);
      }
      String[] values = temp.split(";");
      return values[0];
   }

}
