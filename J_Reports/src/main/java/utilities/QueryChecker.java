package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryChecker {

   // regex to find enclosing set of curly braces aka token
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

}
