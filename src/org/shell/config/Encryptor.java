package org.shell.config;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.log4j.Logger;

public class Encryptor {
	private static Logger log = Logger.getLogger(Encryptor.class);
	private static KeyPairGenerator keyPairGenerator;
	
	static{
		
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		  try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA","BC");
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(7);
		}
	      keyPairGenerator.initialize(2048);
//	      for (Provider p : Security.getProviders())
//	   	      log.info(p.getName());
	     log.info("Using Key Pair Generator : " + keyPairGenerator.getAlgorithm() + " - " + keyPairGenerator.getProvider().getName());
	}
	
	public Encryptor()
	{
	}
	
	public static KeyPair getPairGen() throws NoSuchAlgorithmException
	{
      KeyPair keyPair = keyPairGenerator.generateKeyPair();
      
     // log.info("Generated KP PR: " + new String(keyPair.getPrivate().getEncoded()));
     // log.info("Generated KP PU: " + new String(keyPair.getPrivate().getEncoded()));
      return keyPair;
	}
	/*
	public static PublicKey getPublicKey(KeyPair keyPair) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		 KeyFactory keyFactory = KeyFactory.getInstance("RSA");
         X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
         PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
         return publicKey;
	}
	*/
	
	public static byte[] encrypt(String decrypt,PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException
	{
		log.info("decrypted data : " + decrypt);
		Cipher cipher = Cipher.getInstance("RSA","BC");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] en = cipher.doFinal(decrypt.getBytes());
		log.info("encrypted data : " + en);
		return en;
	}
	
	public static String decrypt(byte[] encrypt,KeyPair keyPair) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException
	{
		log.info("encrypted data : " + encrypt);
		Cipher cipher = Cipher.getInstance("RSA","BC");
		cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        byte[] de = cipher.doFinal(encrypt);
        String decrypt = new String(de);
        log.info("decrypted data : " + decrypt);
        return decrypt;
	}
	
}
