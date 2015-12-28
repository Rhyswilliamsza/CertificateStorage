//RhysIT.co.za
package Interface;

import Backend.CertificateOperations;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Engine extends JFrame {
    static Panels panels;

    public Engine() {
        panels = new Panels();
        setLookAndFeel();
        setJFrameOptions();
    }

    public static void addCertificateButtonClicked(String privateKey, String publicKey, String intermediate) {
        boolean success = CertificateOperations.addCertificatePair(privateKey, publicKey, intermediate);
        if (success) {
            updateTable();
            panels.addCertificatesPrivateKey.setText("");
            panels.addCertificatesPublicKey.setText("");
            panels.addCertificatesIntermediateCert.setText("");
        }
    }

    public static void updateTable() {
        panels.listCertificatesTable.setModel(CertificateOperations.getTableModel(null));
    }

    public static void updateTable(String search) {
        panels.listCertificatesTable.setModel(CertificateOperations.getTableModel(search));
    }

    private static void copyToClipboard(String data) {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = defaultToolkit.getSystemClipboard();
        clipboard.setContents(new StringSelection(data), null);
    }

    public static void copySelectedPrivateKey() {
        int selectedRow = panels.listCertificatesTable.getSelectedRow();
        String selectedDomain = panels.listCertificatesTable.getValueAt(selectedRow, 1).toString();
        String selectedExpiryDate = panels.listCertificatesTable.getValueAt(selectedRow, 2).toString();

        copyToClipboard(CertificateOperations.getCertificatePair(selectedDomain, selectedExpiryDate).getPrivateKey());
    }

    public static void copySelectedPublicKey() {
        int selectedRow = panels.listCertificatesTable.getSelectedRow();
        String selectedDomain = panels.listCertificatesTable.getValueAt(selectedRow, 1).toString();
        String selectedExpiryDate = panels.listCertificatesTable.getValueAt(selectedRow, 2).toString();

        copyToClipboard(CertificateOperations.getCertificatePair(selectedDomain, selectedExpiryDate).getPublicKey());
    }

    public static void deleteSelectedCertificatePair() {
        int selectedRow = panels.listCertificatesTable.getSelectedRow();
        String selectedDomain = panels.listCertificatesTable.getValueAt(selectedRow, 1).toString();
        String selectedExpiryDate = panels.listCertificatesTable.getValueAt(selectedRow, 2).toString();

        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the certificate for " + selectedDomain + " expiring " + selectedExpiryDate + "?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            CertificateOperations.delCertificatePair(selectedDomain, selectedExpiryDate);
            updateTable();
        }
    }

    public static void copySelectedIntermediateCert() {
        int selectedRow = panels.listCertificatesTable.getSelectedRow();
        String selectedDomain = panels.listCertificatesTable.getValueAt(selectedRow, 1).toString();
        String selectedExpiryDate = panels.listCertificatesTable.getValueAt(selectedRow, 2).toString();

        copyToClipboard(CertificateOperations.getCertificatePair(selectedDomain, selectedExpiryDate).getIntermediateCert());
    }

    public static void searchCertificatePair() {
        String term = JOptionPane.showInputDialog("Please enter a domain name to search. Leave blank to show all.");
        updateTable(term); //Update table to display search results
    }

    private void setJFrameOptions() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(initMainPanel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JPanel initMainPanel() {
        JPanel main = new JPanel(new MigLayout());
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon(getClass().getResource("/Interface/Images/logo.png")));
        main.add(logo, "cell 0 0, center");
        main.add(genTabs(), "cell 0 1");
        return main;
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JTabbedPane genTabs() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.setPreferredSize(new Dimension(1000, 600));
        tabs.addTab("List Certificates", panels.ListCertificates());
        tabs.addTab("Add Certificate", panels.AddCertificates());
        return tabs;
    }
}
