package com.vcareinc.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class SecureUtils {
	
	private String keyString = "adkj@#$02#@adflkj)(*jlj@#$#@LKjasdjlkj<.,mo@#$@#kljlkdsu343";
	private static final String charset = "0123456789abcdefghijklmnopqrstuvwxyz";
	
	public final String topSecret = "AFeaf3109234ufaEF";
	
	/**
	 * Encrypts and encodes the Object and IV for url inclusion 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public String[] encryptObject(Object obj) throws Exception {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(stream);
		try {
			// Serialize the object			
			out.writeObject(obj);		
			byte[] serialized = stream.toByteArray();
					
			// Setup the cipher and Init Vector
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        byte[] iv = new byte[cipher.getBlockSize()];
	        new SecureRandom().nextBytes(iv);
	        IvParameterSpec ivSpec = new IvParameterSpec(iv);
	
	        // Hash the key with SHA-256 and trim the output to 128-bit for the key
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        digest.update(keyString.getBytes());
	        byte[] key = new byte[16];
	        System.arraycopy(digest.digest(), 0, key, 0, key.length);
	        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
	   
	        // encrypt
	        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
	       
	        // Encrypt & Encode the input
	        byte[] encrypted = cipher.doFinal(serialized);       
	        byte[] base64Encoded = Base64.encodeBase64(encrypted);
	        String base64String = new String(base64Encoded);
	        String urlEncodedData = URLEncoder.encode(base64String,"UTF-8");
	       
	        // Encode the Init Vector
	        byte[] base64IV = Base64.encodeBase64(iv);
	        String base64IVString = new String(base64IV);
	        String urlEncodedIV = URLEncoder.encode(base64IVString, "UTF-8");
 
	        return new String[] {urlEncodedData, urlEncodedIV};
		}finally {
			stream.close();
			out.close();
		}
	}
	
	/**
	 * Decrypts the String and serializes the object
	 * @param base64Data
	 * @param base64IV
	 * @return
	 * @throws Exception
	 */
	public Object decryptObject(String base64Data, String base64IV) throws Exception {
		// Decode the data
		byte[] encryptedData = Base64.decodeBase64(base64Data.getBytes()); 
		
		// Decode the Init Vector
		byte[] rawIV = Base64.decodeBase64(base64IV.getBytes()); 
		
		// Configure the Cipher
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(rawIV);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(keyString.getBytes());
        byte[] key = new byte[16];
        System.arraycopy(digest.digest(), 0, key, 0, key.length);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		
		// Decrypt the data..
		byte[] decrypted = cipher.doFinal(encryptedData);
		
		// Deserialize the object		
		ByteArrayInputStream stream = new ByteArrayInputStream(decrypted);
		ObjectInput in = new ObjectInputStream(stream);
		Object obj = null;
		try {
			obj = in.readObject(); 
		}finally {
			stream.close();
			in.close();
		}
		return obj;
	}
	
	public static String getRandomPassword(int length) {
	    Random rand = new Random(System.currentTimeMillis());
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < length; i++) {
	        int pos = rand.nextInt(charset.length());
	        sb.append(charset.charAt(pos));
	    }
	    return sb.toString();
	}
	
	public static String generatePassword(String password) {
		String encryptPassword = password;
		StrongPasswordEncryptor strongPasswordEncryptor = new StrongPasswordEncryptor();
		encryptPassword = strongPasswordEncryptor.encryptPassword(encryptPassword);
		return encryptPassword;
	}
	
	public static Boolean checkPassword(String password, String encryptPassword) {
		StrongPasswordEncryptor strongPasswordEncryptor = new StrongPasswordEncryptor();
		if(strongPasswordEncryptor.checkPassword(password, encryptPassword))
			return true;
		else
			return false;
	}
}
