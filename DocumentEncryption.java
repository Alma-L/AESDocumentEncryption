import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DocumentEncryption {
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";


    private static final SecretKeySpec key = Generator.generateKey();
    private static final IvParameterSpec iv = Generator.generateIv();

    public static void encryptToNewFile(File inputFile, File outputFile) {
        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)) {

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] outputBytes = cipher.update(buffer, 0, bytesRead);
                if (outputBytes != null) {
                    outputStream.write(outputBytes);
                }
            }
            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                outputStream.write(outputBytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decryptToNewFile(File inputFile, File outputFile) {
        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)) {

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, key, iv);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] outputBytes = cipher.update(buffer, 0, bytesRead);
                if (outputBytes != null) {
                    outputStream.write(outputBytes);
                }
            }
            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                outputStream.write(outputBytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
