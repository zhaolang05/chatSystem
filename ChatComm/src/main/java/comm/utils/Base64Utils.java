package comm.utils;



import java.util.Base64;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Base64 Encryption and decryption tool class
 */

public class Base64Utils {



    /**
     * Strings to Base64 encoded
     * */

    public static String stringToBase64(String string){
        String encodedString = Base64.getEncoder().encodeToString(string.getBytes());
        return  encodedString;
    }

    /**
     * Base64 to Strings  decoded
     * */

    public static String base64ToString(String string){
        String decodedString =new String(Base64.getDecoder().decode(string));
        return decodedString;
    }

    /**
     * file to Base64 encode
     * */

    public static String fileToBase64(File file){
        String encodedString="";
        FileInputStream inputFile = null;
        try {
            inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            encodedString = Base64.getEncoder().encodeToString(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    /**
     * base64 to file decode
     * */

    public static void base64ToFile(String string,File file){
        FileOutputStream fos = null;
        try {
            byte[] decodeBytes = Base64.getDecoder().decode(string.getBytes());
            fos = new FileOutputStream(file);
            fos.write(decodeBytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}