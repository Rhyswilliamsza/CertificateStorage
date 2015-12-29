//RhysIT.co.za
package Application;

import Backend.CertificateOperations;
import Backend.DatabaseOperations;

import javax.swing.*;
import java.io.*;

public class Main {
    //Run the program
    public static void main(String[] args) {
        genFolders();
        backupDatabase();
        addShutdownHook();

        DatabaseOperations.connect(); //Connect to database
        CertificateOperations.populateCertificatesFromDB(); //Populate list from database
        new Interface.Engine();
    }

    //Generate the 'Data' directory for files
    private static void genFolders() {
        new File("Data").mkdirs();
    }

    //Add a shutdown hook to close the database before exiting
    private static void addShutdownHook() {
        Thread test = new Thread(new Application.ShutdownThread());
        Runtime.getRuntime().addShutdownHook(test);
    }

    //Backup database to _backup file upon program startup
    private static void backupDatabase() {
        try {
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
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
