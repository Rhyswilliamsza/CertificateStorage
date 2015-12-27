//RhysIT.co.za
package Interface;

import Backend.CertificateOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Launcher extends JFrame {
    static Panels panels;

    public Launcher() {
        panels = new Panels();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(genTabs());
        this.pack();
        this.setVisible(true);
    }

    private JTabbedPane genTabs() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.setPreferredSize(new Dimension(700, 700));
        tabs.addTab("List Certificates", panels.ListCertificates());
        tabs.addTab("Add Certificate", panels.AddCertificates());
        return tabs;
    }

    public static void addCertificateButtonClicked(String privateKey, String publicKey) {
        boolean success = CertificateOperations.addCertificate(privateKey, publicKey);
        if (success) {
            panels.listCertificatesTable.setModel(CertificateOperations.getTableModel());
            panels.addCertificatesPrivateKey.setText("");
            panels.addCertificatesPublicKey.setText("");
        }
    }

    private static void copyToClipboard (String data) {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = defaultToolkit.getSystemClipboard();
        clipboard.setContents(new StringSelection(data), null);
    }

    public static void copyPrivateKey () {
        int selectedRow = panels.listCertificatesTable.getSelectedRow();
        String selectedDomain = panels.listCertificatesTable.getValueAt (selectedRow, 1).toString();
        String selectedExpiryDate = panels.listCertificatesTable.getValueAt(selectedRow, 2).toString();

        copyToClipboard(CertificateOperations.getCertificatePair(selectedDomain, selectedExpiryDate).getPrivateKey());
    }

    public static void copyPublicKey () {
        int selectedRow = panels.listCertificatesTable.getSelectedRow();
        String selectedDomain = panels.listCertificatesTable.getValueAt (selectedRow, 1).toString();
        String selectedExpiryDate = panels.listCertificatesTable.getValueAt(selectedRow, 2).toString();

        copyToClipboard(CertificateOperations.getCertificatePair(selectedDomain, selectedExpiryDate).getPublicKey());
    }
}
