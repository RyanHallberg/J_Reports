package utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHasher {

   public static String getCryptoHash(String input, String algorithm){

      try {
         //MessageDigest classes Static getInstance method is called with MD5 hashing
         MessageDigest msgDigest = MessageDigest.getInstance(algorithm);

         byte[] b = input.getBytes();
         byte[] inputDigest = msgDigest.digest(b);

         // Convert byte array into signum representation
         // BigInteger class is used, to convert the resultant byte array into its signum representation
         BigInteger inputDigestBigInt = new BigInteger(1, inputDigest);

         // Convert the input digest into hex value
         String hashtext = inputDigestBigInt.toString(16);

         //Add preceding 0's to pad the hashtext to make it 32 bit
         while (hashtext.length() < 32) {
             hashtext = "0" + hashtext;
         }
         return hashtext;
     }
     // Catch block to handle the scenarios when an unsupported message digest algorithm is provided.
     catch (NoSuchAlgorithmException e) {
         throw new RuntimeException(e);
     }
   }
   
   public static String createSalt() {
		byte[] bytes = new byte[10];
        SecureRandom random = new SecureRandom();

        random.nextBytes(bytes);
        return bytes.toString();
	}
}
