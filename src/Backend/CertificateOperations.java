//RhysIT.co.za
package Backend;

import Objects.CertificatePair;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
            System.out.println("Database fetched");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Save certificates back to SQLite DB
    public static void saveCertificates(List<CertificatePair> certificates) {
        for (int i = 0; i < certificates.size(); i++) {
            DatabaseOperations.queryVoid("INSERT OR IGNORE INTO certificates (private, public) VALUES ('" + certificates.get(i).getPrivateKey() + "', '" + certificates.get(i).getPublicKey() + "')");
        }
        System.out.println("Database updated");
    }

    //Add certificate to list
    public static boolean addCertificate(String privateKey, String publicKey) {
        if (!privateKey.contains("-----BEGIN RSA PRIVATE KEY-----")) {
            JOptionPane.showMessageDialog(null, "Invalid Private Key!");
        } else {
            if (!publicKey.contains("-----BEGIN CERTIFICATE-----")) {
                JOptionPane.showMessageDialog(null, "Invalid Public Key!");
            } else {
                certificates.add(new CertificatePair(privateKey, publicKey));
                saveCertificates(certificates); //Redundant saving
                JOptionPane.showMessageDialog(null, "Certificate Saved!");
                return true;
            }
        }
        return false;
    }

    //Convert list to Table Model
    public static DefaultTableModel getTableModel() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int j) {
                return false;
            }
        };

        model.addColumn("Base Domain");
        model.addColumn("Domain");
        model.addColumn("Expiry");
        model.addColumn("Validity");

        //Populate table with information
        for (int i = 0; i < certificates.size(); i++) {
            CertificatePair current = certificates.get(i);
            model.addRow(new Object[]{current.getDomainBaseFirst(), current.getDomainName(), current.getExpiryDate(), current.getValidity()});
        }

        return model;
    }

    public static CertificatePair getCertificatePair(String domain, String expiry) {
        for (int i = 0; i < certificates.size(); i++) {
            if (domain.equals(certificates.get(i).getDomainName()) && expiry.equals(certificates.get(i).getExpiryDate())) {
                return certificates.get(i);
            }
        }
        return null;
    }

}
