//RhysIT.co.za
package Interface;

import Backend.CertificateOperations;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Launcher extends JFrame {
    static Panels panels;

    public Launcher() {
        panels = new Panels();
        setLookAndFeel();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(initMainPanel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void addCertificateButtonClicked(String privateKey, String publicKey, String intermediate) {
        boolean success = CertificateOperations.addCertificate(privateKey, publicKey, intermediate);
        if (success) {
            panels.listCertificatesTable.setModel(CertificateOperations.getTableModel());
            panels.addCertificatesPrivateKey.setText("");
            panels.addCertificatesPublicKey.setText("");
            panels.addCertificatesIntermediateCert.setText("");
        }
    }

    private static void copyToClipboard(String data) {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = defaultToolkit.getSystemClipboard();
        clipboard.setContents(new StringSelection(data), null);
    }

    public static void copyPrivateKey() {
        int selectedRow = panels.listCertificatesTable.getSelectedRow();
        String selectedDomain = panels.listCertificatesTable.getValueAt(selectedRow, 1).toString();
        String selectedExpiryDate = panels.listCertificatesTable.getValueAt(selectedRow, 2).toString();

        copyToClipboard(CertificateOperations.getCertificatePair(selectedDomain, selectedExpiryDate).getPrivateKey());
    }

    public static void copyPublicKey() {
        int selectedRow = panels.listCertificatesTable.getSelectedRow();
        String selectedDomain = panels.listCertificatesTable.getValueAt(selectedRow, 1).toString();
        String selectedExpiryDate = panels.listCertificatesTable.getValueAt(selectedRow, 2).toString();

        copyToClipboard(CertificateOperations.getCertificatePair(selectedDomain, selectedExpiryDate).getPublicKey());
    }

    public static void copyIntermediateCert() {
        int selectedRow = panels.listCertificatesTable.getSelectedRow();
        String selectedDomain = panels.listCertificatesTable.getValueAt(selectedRow, 1).toString();
        String selectedExpiryDate = panels.listCertificatesTable.getValueAt(selectedRow, 2).toString();

        copyToClipboard(CertificateOperations.getCertificatePair(selectedDomain, selectedExpiryDate).getIntermediateCert());
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
