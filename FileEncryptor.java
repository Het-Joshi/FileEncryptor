package Main;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class FileEncryptor {

	private static Key key;

	public static void EncryptFile(String inputFilePath, String outputFilePath) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] inputFileBytes = Files.readAllBytes(Paths.get(inputFilePath));
		byte[] encryptedBytes = cipher.doFinal(inputFileBytes);
		
		Files.write(Paths.get(outputFilePath), encryptedBytes, StandardOpenOption.CREATE);
	}

	public static void generateKey(String value) {
		try {
			byte[] keyBytes = value.getBytes(StandardCharsets.UTF_8);
			byte[] hashedKey = MessageDigest.getInstance("SHA-256").digest(keyBytes);

			key = new SecretKeySpec(hashedKey, 0, hashedKey.length, "AES");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
