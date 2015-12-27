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

    public JPanel AddCertificates() {
        addCertificatesPanel = new JPanel(new MigLayout());

        JLabel privateKeyLabel = new JLabel("Private Key");
        addCertificatesPanel.add(privateKeyLabel, "cell 0 0");

        JLabel publicKeyLabel = new JLabel("Public Key");
        addCertificatesPanel.add(publicKeyLabel, "cell 1 0");

        addCertificatesPrivateKey = new JTextArea();
        addCertificatesPrivateKey.setFont(new Font("Arial", Font.PLAIN, 6));
        addCertificatesPrivateKey.setPreferredSize(new Dimension(340, 500));
        addCertificatesPanel.add(addCertificatesPrivateKey, "cell 0 1");

        addCertificatesPublicKey = new JTextArea();
        addCertificatesPublicKey.setFont(new Font("Arial", Font.PLAIN, 6));
        addCertificatesPublicKey.setPreferredSize(new Dimension(340, 500));
        addCertificatesPanel.add(addCertificatesPublicKey, "cell 1 1");

        JButton addButton = new JButton("Add to database");
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Launcher.addCertificateButtonClicked(addCertificatesPrivateKey.getText(), addCertificatesPublicKey.getText());
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

        JScrollPane listScroll = new JScrollPane(listCertificatesTable);
        listScroll.setPreferredSize(new Dimension(700, 700));
        listCertificatesPanel.add(listScroll, "cell 0 1");
        return listCertificatesPanel;
    }
}
