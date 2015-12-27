package Interface;

import Backend.CertificateOperations;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Panels {
    JTable listCertificatesTable;
    JPanel listCertificatesPanel;

    JPanel addCertificatesPanel;
    JTextArea addCertificatesPrivateKey;
    JTextArea addCertificatesPublicKey;
    JTextArea addCertificatesIntermediateCert;

    public JPanel AddCertificates() {
        addCertificatesPanel = new JPanel(new MigLayout());

        JLabel privateKeyLabel = new JLabel("Private Key");
        addCertificatesPanel.add(privateKeyLabel, "cell 0 0");

        JLabel publicKeyLabel = new JLabel("Public Key");
        addCertificatesPanel.add(publicKeyLabel, "cell 1 0");

        JLabel intermediateCertLabel = new JLabel("Intermediate Certificate");
        addCertificatesPanel.add(intermediateCertLabel, "cell 2 0");

        addCertificatesPrivateKey = new JTextArea();
        addCertificatesPrivateKey.setFont(new Font("Arial", Font.PLAIN, 7));
        addCertificatesPrivateKey.setPreferredSize(new Dimension(350, 600));
        addCertificatesPanel.add(addCertificatesPrivateKey, "cell 0 1");

        addCertificatesPublicKey = new JTextArea();
        addCertificatesPublicKey.setFont(new Font("Arial", Font.PLAIN, 7));
        addCertificatesPublicKey.setPreferredSize(new Dimension(350, 600));
        addCertificatesPanel.add(addCertificatesPublicKey, "cell 1 1");

        addCertificatesIntermediateCert = new JTextArea();
        addCertificatesIntermediateCert.setFont(new Font("Arial", Font.PLAIN, 7));
        addCertificatesIntermediateCert.setPreferredSize(new Dimension(350, 600));
        addCertificatesPanel.add(addCertificatesIntermediateCert, "cell 2 1");

        JButton addButton = new JButton("Add to database");
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Launcher.addCertificateButtonClicked(addCertificatesPrivateKey.getText(), addCertificatesPublicKey.getText(), addCertificatesIntermediateCert.getText());
            }
        });
        addCertificatesPanel.add(addButton, "cell 0 2");

        return addCertificatesPanel;
    }

    public JPanel ListCertificates() {
        listCertificatesTable = new JTable(CertificateOperations.getTableModel());
        listCertificatesPanel = new JPanel(new MigLayout());

        listCertificatesTable.setRowHeight(25);
        listCertificatesTable.setAutoCreateRowSorter(true);

        JButton copyPrivateKey = new JButton("Copy Private Key");
        copyPrivateKey.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Launcher.copyPrivateKey();
            }
        });
        listCertificatesPanel.add(copyPrivateKey, "cell 0 0");

        JButton copyPublicKey = new JButton("Copy Public Key");
        copyPublicKey.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Launcher.copyPublicKey();
            }
        });
        listCertificatesPanel.add(copyPublicKey, "cell 0 0");

        JButton copyIntermediateCert = new JButton("Copy Intermediate Certificate");
        copyIntermediateCert.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Launcher.copyIntermediateCert();
            }
        });
        listCertificatesPanel.add(copyIntermediateCert, "cell 0 0");

        JScrollPane listScroll = new JScrollPane(listCertificatesTable);
        listScroll.setPreferredSize(new Dimension(1000, 700));
        listCertificatesPanel.add(listScroll, "cell 0 1");
        return listCertificatesPanel;
    }
}
