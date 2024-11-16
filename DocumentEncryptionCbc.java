import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class DocumentEncryptionCbc {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public static void encrypt(File inputFile) {
        try {
            byte[] ivBytes = new byte[16];
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(ivBytes);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);

            // Generate a random key
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            keyGen.init(256); // For a 256-bit AES key
            SecretKey secretKey = keyGen.generateKey();

            File encryptedFile = new File(inputFile.getAbsolutePath() + ".encrypted");

            encryptToNewFile(inputFile, encryptedFile, secretKey, iv);

            try (FileOutputStream ivOut = new FileOutputStream("iv.key");
                 FileOutputStream keyOut = new FileOutputStream("secret.key")) {
                ivOut.write(ivBytes);
                keyOut.write(secretKey.getEncoded());
            }

            renameToOldFilename(inputFile, encryptedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decrypt(File inputFile) {
        try {
            // Load IV and key
            byte[] ivBytes = new byte[16];
            byte[] keyBytes = new byte[32];
            try (FileInputStream ivIn = new FileInputStream("iv.key");
                 FileInputStream keyIn = new FileInputStream("secret.key")) {
                ivIn.read(ivBytes);
                keyIn.read(keyBytes);
            }
            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, ALGORITHM);

            File decryptedFile = new File(inputFile.getAbsolutePath() + ".decrypted");

            decryptToNewFile(inputFile, decryptedFile, secretKey, iv);

            renameToOldFilename(inputFile, decryptedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void encryptToNewFile(File inputFile, File outputFile, SecretKey secretKey, IvParameterSpec iv) {
        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(cipher.update(buffer, 0, bytesRead));
            }
            outputStream.write(cipher.doFinal());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void decryptToNewFile(File inputFile, File outputFile, SecretKey secretKey, IvParameterSpec iv) {
        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(cipher.update(buffer, 0, bytesRead));
            }
            outputStream.write(cipher.doFinal());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void renameToOldFilename(File oldFile, File newFile) {
        if (oldFile.exists()) {
            oldFile.delete();
        }
        newFile.renameTo(oldFile);
    }
}
