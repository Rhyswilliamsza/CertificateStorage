//RhysIT.co.za
package Engine;

import Backend.CertificateOperations;
import Backend.DatabaseOperations;

import javax.swing.*;
import java.io.*;

public class Engine {
    public static void main(String[] args) {
        genFoldersAndBackup();
        DatabaseOperations.connect();
        CertificateOperations.populateCertificates();
        new Interface.Launcher();

        //DatabaseOperations.disconnect();
    }

    private static void genFoldersAndBackup() {
        try {
            new File("Data").mkdirs();
            InputStream original = new FileInputStream("Data/Certificates.db");
            OutputStream copy = new FileOutputStream("Data/Certificates.db_backup");
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = original.read(buf)) > 0) {
                copy.write(buf, 0, bytesRead);
            }
            original.close();
            copy.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);}
    }
}
