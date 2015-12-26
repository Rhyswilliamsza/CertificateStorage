//RhysIT.co.za
package Objects;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

public class CertificatePair {
    String privateKey;
    String publicKey;
    X509Certificate cert;

    public CertificatePair(String privateK, String publicK) {
        privateKey = privateK;
        publicKey = publicK;

        try {
            CertificateFactory certFact = CertificateFactory.getInstance("X.509");
            cert = (X509Certificate) certFact.generateCertificate(new ByteArrayInputStream(publicKey.getBytes()));
        } catch (CertificateException e) {
            JOptionPane.showMessageDialog(null, "That certificate is not valid and has not been added to the database!");
            cert = null;
            e.printStackTrace();
        }
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public Date getExpiryDate() {
        return cert.getNotAfter();
    }

    public String getDomainName() {
        return cert.getSubjectDN().getName();
    }
}
