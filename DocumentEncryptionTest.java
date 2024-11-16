import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class DocumentEncryptionTest {
    public static void main(String[] args) {
        // Krijo një skedar me përmbajtje "SHIHEMI NE ROUTE 66 NE ORA 6"
        File testFile = new File("testFile.txt");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("SHIHEMI NE ROUTE 66 NE ORA 6");  // Shkruaj përmbajtjen "SHIHEMI NE ROUTE 66 NE ORA 6"
            System.out.println("Skedari origjinal u krijua me sukses!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Printoni përmbajtjen e skedarit të krijuar
        System.out.println("Përmbajtja e skedarit origjinal:");
        try (FileReader reader = new FileReader(testFile)) {
            int content;
            while ((content = reader.read()) != -1) {
                System.out.print((char) content);  // Lexo dhe printo karakterin nga skedari
            }
            System.out.println();  // Shto një vijë të re pas përmbajtjes
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Enkripto skedarin dhe krijo një skedar të ri të enkriptuar
        System.out.println("\nDuke enkriptuar skedarin...");
        File encryptedFile = new File("testFile_encrypted.txt"); // Emri i ri i skedarit të enkriptuar
        DocumentEncryption.encryptToNewFile(testFile, encryptedFile);  // Enkriptimi dhe ruajtja në skedarin e ri

        // Kontrollo dhe printo përmbajtjen e skedarit të enkriptuar
        System.out.println("Përmbajtja e skedarit të enkriptuar:");
        try (FileReader reader = new FileReader(encryptedFile)) {
            int content;
            while ((content = reader.read()) != -1) {
                System.out.print((char) content);  // Lexo dhe printo karakterin nga skedari i enkriptuar
            }
            System.out.println();  // Shto një vijë të re pas përmbajtjes
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Dekripto skedarin dhe krijo një skedar të ri të dekriptuar
        System.out.println("\nDuke dekriptuar skedarin...");
        File decryptedFile = new File("testFile_decrypted.txt"); // Emri i ri i skedarit të dekriptuar
        DocumentEncryption.decryptToNewFile(encryptedFile, decryptedFile);  // Dekriptimi dhe ruajtja në skedarin e ri

        // Kontrollo dhe printo përmbajtjen e skedarit të dekriptuar
        System.out.println("Përmbajtja e skedarit të dekriptuar:");
        try (FileReader reader = new FileReader(decryptedFile)) {
            int content;
            while ((content = reader.read()) != -1) {
                System.out.print((char) content);  // Lexo dhe printo karakterin nga skedari i dekriptuar
            }
            System.out.println();  // Shto një vijë të re pas përmbajtjes
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
