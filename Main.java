import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        File testFile = new File("testFileCbc.txt");

        // Krijo skedarin origjinal
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("SHIHEMI NE ROUTE 66 NE ORA 6");
            System.out.println("Skedari origjinal u krijua me sukses!");
        } catch (IOException e) {
            System.out.println("Gabim gjatë krijimit të skedarit: " + e.getMessage());
        }

        // Printo përmbajtjen e skedarit origjinal
        System.out.println("\nPërmbajtja e skedarit origjinal:");
        printFileContentAsText(testFile);

        // Enkriptimi
        System.out.println("\nDuke enkriptuar...");
        DocumentEncryptionCbc.encrypt(testFile);
        System.out.println("Enkriptimi përfundoi!");

        // Printo përmbajtjen e skedarit të enkriptuar
        System.out.println("\nPërmbajtja e skedarit të enkriptuar:");
        printFileContentAsBytes(testFile);

        // Dekriptimi
        System.out.println("\nDuke dekriptuar...");
        DocumentEncryptionCbc.decrypt(testFile);
        System.out.println("Dekriptimi përfundoi!");

        // Printo përmbajtjen e skedarit të dekriptuar
        System.out.println("\nPërmbajtja e skedarit të dekriptuar:");
        printFileContentAsText(testFile);
    }

    // Metodë për të lexuar dhe printuar përmbajtjen e skedarit si tekst
    private static void printFileContentAsText(File file) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            int content;
            while ((content = inputStream.read()) != -1) {
                System.out.print((char) content);
            }
            System.out.println(); // Shto një vijë të re
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
}
