import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class PDFDocumentEncryption {
    public static void main(String[] args) {
        File pdfFile = new File("BookForEncryption.pdf");

        if (!pdfFile.exists()) {
            System.out.println("PDF file does not exist. Please ensure 'BookForEncryption.pdf' is in the specified directory.");
            return;
        }

        System.out.println("Original PDF file size: " + pdfFile.length() + " bytes");

        File encryptedFile = new File("BookForEncryption_encrypted.pdf");
        File decryptedFile = new File("BookForEncryption_decrypted.pdf");

        // Encrypt the file
        System.out.println("\nEncrypting the PDF file...");
        DocumentEncryption.encryptToNewFile(pdfFile, encryptedFile);

        if (encryptedFile.exists()) {
            System.out.println("Encrypted file created: " + encryptedFile.getName());
            System.out.println("Encrypted file size: " + encryptedFile.length() + " bytes");
        } else {
            System.out.println("Encryption failed!");
            return;
        }

        // Decrypt the file
        System.out.println("\nDecrypting the PDF file...");
        DocumentEncryption.decryptToNewFile(encryptedFile, decryptedFile);

        if (decryptedFile.exists()) {
            System.out.println("Decrypted file created: " + decryptedFile.getName());
            System.out.println("Decrypted file size: " + decryptedFile.length() + " bytes");
        } else {
            System.out.println("Decryption failed!");
            return;
        }

        // Compare original and decrypted files
        try {
            byte[] originalBytes = Files.readAllBytes(pdfFile.toPath());
            byte[] decryptedBytes = Files.readAllBytes(decryptedFile.toPath());

            if (Arrays.equals(originalBytes, decryptedBytes)) {
                System.out.println("\nTest Passed: Decrypted file matches the original file.");
            } else {
                System.out.println("\nTest Failed: Decrypted file does not match the original file.");
            }
        } catch (IOException e) {
            System.out.println("Error comparing files: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
