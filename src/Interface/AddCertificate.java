//RhysIT.co.za
package Interface;

import Backend.CertificateOperations;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddCertificate extends JFrame {
    public AddCertificate() {
        JPanel panel = makePanel();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setVisible(true);
    }

    private JPanel makePanel() {
        JPanel panel = new JPanel(new MigLayout());

        JLabel privateKeyLabel = new JLabel("Private Key");
        panel.add(privateKeyLabel, "cell 0 0");

        JLabel publicKeyLabel = new JLabel("Public Key");
        panel.add(publicKeyLabel, "cell 1 0");

        JTextArea privateKey = new JTextArea();
        privateKey.setFont(new Font("Arial", Font.PLAIN, 8));
        privateKey.setPreferredSize(new Dimension(300, 500));
        panel.add(privateKey, "cell 0 1");

        JTextArea publicKey = new JTextArea();
        publicKey.setFont(new Font("Arial", Font.PLAIN, 8));
        publicKey.setPreferredSize(new Dimension(300, 500));
        panel.add(publicKey, "cell 1 1");

        JButton addButton = new JButton("Add to database");
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                mouseClickAction (privateKey.getText(), publicKey.getText());
            }
        });
        panel.add(addButton, "cell 0 2");

        return panel;
    }

    private void mouseClickAction (String privateKey, String publicKey)
    {
        CertificateOperations.addCertificate(privateKey, publicKey);
    }
}
