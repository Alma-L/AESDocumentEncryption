import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class TextDocumentEncryption {
    public static void main(String[] args) {
        File textFile = new File("TextForEncryption.txt");
        File encryptedFile = new File(textFile.getAbsolutePath() + ".encrypted");
        File decryptedFile = new File(textFile.getAbsolutePath() + ".decrypted");

        // Krijo skedarin origjinal
        try (FileWriter writer = new FileWriter(textFile)) {
            writer.write("SHIHEMI NE ROUTE 66 NE ORA 6");
            System.out.println("Skedari origjinal u krijua me sukses!");
        } catch (IOException e) {
            System.out.println("Gabim gjatë krijimit të skedarit: " + e.getMessage());
        }
        System.out.println("\nPërmbajtja e skedarit origjinal:");
        printFileContentAsText(textFile);

        // Enkriptimi
        System.out.println("\n\nDuke enkriptuar...");
        DocumentEncryption.encryptToNewFile(textFile, encryptedFile);
        renameToOldFilename(textFile, encryptedFile);
        System.out.println("Enkriptimi përfundoi!");
        System.out.println("\nPërmbajtja e skedarit të enkriptuar:");
        printFileContentAsBytes(textFile);

        // Dekriptimi
        System.out.println("\n\nDuke dekriptuar...");
        DocumentEncryption.decryptToNewFile(textFile, decryptedFile);
        renameToOldFilename(textFile, decryptedFile);
        System.out.println("Dekriptimi përfundoi!");
        System.out.println("\nPërmbajtja e skedarit të dekriptuar:");
        printFileContentAsText(textFile);
    }

    private static void printFileContentAsText(File file) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            int content;
            while ((content = inputStream.read()) != -1) {
                System.out.print((char) content);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Gabim gjatë leximit të skedarit: " + e.getMessage());
        }
    }

    // Metodë për të lexuar dhe printuar përmbajtjen e skedarit si byte (Base64)
    private static void printFileContentAsBytes(File file) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] fileBytes = inputStream.readAllBytes();
            String base64Encoded = Base64.getEncoder().encodeToString(fileBytes);
            System.out.println(base64Encoded);
        } catch (IOException e) {
            System.out.println("Gabim gjatë leximit të skedarit: " + e.getMessage());
        }
    }

    private static void renameToOldFilename(File oldFile, File newFile) {
        if (oldFile.exists()) {
            oldFile.delete();
        }
        newFile.renameTo(oldFile);
    }
}
