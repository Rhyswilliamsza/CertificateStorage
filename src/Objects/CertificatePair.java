//RhysIT.co.za
package Objects;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.security.cert.*;
import java.text.SimpleDateFormat;

public class CertificatePair {
    String privateKey;
    String publicKey;
    String intermediateCert;
    X509Certificate cert;

    public CertificatePair(String privateK, String publicK, String intermediate) {
        privateKey = privateK;
        publicKey = publicK;
        intermediateCert = intermediate;

        try {
            CertificateFactory certFact = CertificateFactory.getInstance("X.509");
            cert = (X509Certificate) certFact.generateCertificate(new ByteArrayInputStream(publicKey.getBytes()));
        } catch (CertificateException e) {
            JOptionPane.showMessageDialog(null, "That certificate is not valid and has not been added to the database!");
            e.printStackTrace();
        }
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getIntermediateCert() {
        return intermediateCert;
    }

    public String getExpiryDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(cert.getNotAfter());
    }

    public String getDomainName() {
        try {
            String domain = cert.getSubjectDN().getName().substring(cert.getSubjectDN().getName().indexOf("CN=") + 3);
            if (domain.contains(",")) {
                return domain.substring(0, domain.indexOf(","));
            } else {
                return domain;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something bad happened\n" + e);
            return null;
        }
    }

    public String getDomainBaseFirst() {
        String domain = getDomainName();
        String prefix = domain.substring(0, domain.indexOf("."));
        String base = domain.substring(domain.indexOf(".") + 1);

        return base + "_" + prefix;
    }

    public String getValidity() {
        try {
            cert.checkValidity();
            return "Yes";
        } catch (CertificateExpiredException e) {
            return "Expired";
        } catch (CertificateNotYetValidException e) {
            return "Not Yet";
        }
    }
}
