package GB;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class guestBoardPasswordEncrypt {
	
	public String encrypt(String pw) throws NoSuchAlgorithmException{
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(pw.getBytes());
		
		return bytesToHex(messageDigest.digest());
	}
	
	private String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for(byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

}
