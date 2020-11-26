package DAO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * The purpose of this class is to hash passwords with md5
 * @author Chris Hinson
 * code inspired By Lokesh Gupta
 * on https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/#md5
 * accessed on 11/12/2019
 */
public class PasswordHasher {
    /**
     * Constructor, no arguments to construct the isbn validator
     */
    public PasswordHasher(){}

    /** The purpose of this function is to hash a string with md5
     * @param originalPassword a string representing a users password
     * @return a string representing the original password hashed into md5
     */
    public String md5(String originalPassword) {
    String generatedPassword= null;

    try {
        // Create MessageDigest instance for MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        //Add password bytes to digest
        md.update(originalPassword.getBytes());
        //Get the hash's bytes
        byte[] bytes = md.digest();
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        //Get complete hashed password in hex format
        generatedPassword = sb.toString();
    }
    catch (NoSuchAlgorithmException e)
    {
        e.printStackTrace();
    }
    return generatedPassword;
}
}

