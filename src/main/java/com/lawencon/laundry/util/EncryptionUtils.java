package com.lawencon.laundry.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

/**
 * @author Rian Rivaldo
 */
public class EncryptionUtils {

	public static String encrypt(String str) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.reset();
		messageDigest.update(str.getBytes());
		byte[] digest = messageDigest.digest();
		return DatatypeConverter.printHexBinary(digest);
	}

}
