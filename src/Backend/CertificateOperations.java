//RhysIT.co.za
package Backend;

import Objects.CertificatePair;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CertificateOperations {
    static List<CertificatePair> certificates;

    //Populate the certificates list from the SQLite DB
    public static void populateCertificates() {
        certificates = new ArrayList<>();
        try {
            ResultSet rs = DatabaseOperations.queryResultSet("SELECT * FROM certificates");
            while (rs.next()) {
                certificates.add(new CertificatePair(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Return a list of certificate objects
    public static List<CertificatePair> getCertificates() {
        return certificates;
    }

    //Save certificates back to SQLite DB
    public static void saveCertificates(List<CertificatePair> certificates) {
        for (int i = 0; i < certificates.size(); i++) {
            DatabaseOperations.queryVoid("INSERT OR IGNORE INTO certificates (private, public) VALUES ('" + certificates.get(i).getPrivateKey() + "', '" + certificates.get(i).getPublicKey() + "')");
        }
    }

    //Add certificate to list
    public static boolean addCertificate(String privateKey, String publicKey) {
        if (!privateKey.contains("-----BEGIN CERTIFICATE-----")) {
            JOptionPane.showMessageDialog(null, "Invalid Private Key!");
        } else if (!publicKey.contains("-----BEGIN CERTIFICATE-----")) {
            JOptionPane.showMessageDialog(null, "Invalid Public Key!");
        } else {
            certificates.add(new CertificatePair(privateKey, publicKey));
            //saveCertificates(certificates); //Redundant saving
            JOptionPane.showMessageDialog(null, "Certificate Saved!");
            return true;
        }
        return false;
    }
}
