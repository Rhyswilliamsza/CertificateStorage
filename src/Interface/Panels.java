package Interface;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Panels {
    protected JTable listCertificatesTable;
    protected JPanel listCertificatesPanel;

    protected JPanel addCertificatesPanel;
    protected JTextArea addCertificatesPrivateKey;
    protected JTextArea addCertificatesPublicKey;
    protected JTextArea addCertificatesIntermediateCert;

    //Generate the addCertificates Panel
    protected JPanel AddCertificates() {
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
                Engine.addCertificateButtonClicked(addCertificatesPrivateKey.getText(), addCertificatesPublicKey.getText(), addCertificatesIntermediateCert.getText());
            }
        });
        addCertificatesPanel.add(addButton, "cell 0 2");

        return addCertificatesPanel;
    }

    //Generate the listCertificates Panel
    protected JPanel ListCertificates() {
        listCertificatesTable = new JTable();
        listCertificatesPanel = new JPanel(new MigLayout());
        Engine.updateTable();

        listCertificatesTable.setRowHeight(25);
        listCertificatesTable.setAutoCreateRowSorter(true);

        JButton copyPrivateKey = new JButton("Copy Private Key");
        copyPrivateKey.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Engine.copySelectedPrivateKey();
            }
        });
        listCertificatesPanel.add(copyPrivateKey, "cell 0 0, center");

        JButton copyPublicKey = new JButton("Copy Public Key");
        copyPublicKey.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Engine.copySelectedPublicKey();
            }
        });
        listCertificatesPanel.add(copyPublicKey, "cell 0 0, center");

        JButton copyIntermediateCert = new JButton("Copy Intermediate Certificate");
        copyIntermediateCert.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Engine.copySelectedIntermediateCert();
            }
        });
        listCertificatesPanel.add(copyIntermediateCert, "cell 0 0, center");

        JButton deleteCert = new JButton("Delete Certificate");
        deleteCert.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Engine.deleteSelectedCertificatePair();
            }
        });
        listCertificatesPanel.add(deleteCert, "cell 0 0, center");

        JButton searchCert = new JButton("Search for certificate");
        searchCert.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Engine.searchCertificatePair();
            }
        });
        listCertificatesPanel.add(searchCert, "cell 0 0, center");

        JScrollPane listScroll = new JScrollPane(listCertificatesTable);
        listScroll.setPreferredSize(new Dimension(1000, 700));
        listCertificatesPanel.add(listScroll, "cell 0 1");
        return listCertificatesPanel;
    }
}
