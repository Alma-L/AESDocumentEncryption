import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class Generator {

    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 256;

    public static SecretKeySpec generateKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            keyGen.init(KEY_SIZE);
            SecretKey secretKey = keyGen.generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
        } catch (Exception e) {
            throw new RuntimeException("Error generating key", e);
        }
    }

    public static IvParameterSpec generateIv() {
        byte[] ivBytes = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(ivBytes);
        return new IvParameterSpec(ivBytes);
    }
}
