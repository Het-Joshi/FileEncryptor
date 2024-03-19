package Main;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class FileEncryptor {
	private static final String ALGORITHM = "AES";
	private static final String ENCRYPTION_KEY = "yourEncryptionKey";
	
	public static void EncryptFile(String inputFilePath, String outputFilePath) throws Exception {
		Key key = generateKey();
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] inputFileBytes = Files.readAllBytes(Paths.get(inputFilePath));
		byte[] encryptedBytes = cipher.doFinal(inputFileBytes);
		
		Files.write(Paths.get(outputFilePath), encryptedBytes, StandardOpenOption.CREATE);
	}

	private static Key generateKey() throws Exception {
		byte[] key = ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8);
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16);
		return new SecretKeySpec(key,ALGORITHM);
	}
}
