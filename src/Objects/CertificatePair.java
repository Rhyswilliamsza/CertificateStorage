//RhysIT.co.za
package Objects;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.security.cert.*;
import java.text.SimpleDateFormat;

public class CertificatePair {
    private String privateKey;
    private String publicKey;
    private String intermediateCert;
    private X509Certificate cert;

    //Init Certificate Pair
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

    //Return the public key
    public String getPublicKey() {
        return publicKey;
    }

    //Return the private key
    public String getPrivateKey() {
        return privateKey;
    }

    //Return the intermediate certificate
    public String getIntermediateCert() {
        return intermediateCert;
    }

    //Return the expiry date of the certificate in String format
    public String getExpiryDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(cert.getNotAfter());
    }

    //Return the domain name for which the certificate is valid
    public String getDomainName() {
        try {
            String domain = cert.getSubjectDN().getName().substring(cert.getSubjectDN().getName().indexOf("CN=") + 3); //Separate the domain from the certificate subject
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

    //Return the domain name in the form (basedomain.ext_prefix)
    public String getDomainBaseFirst() {
        String domain = getDomainName();
        String prefix = domain.substring(0, domain.indexOf(".")); //Extract prefix
        String base = domain.substring(domain.indexOf(".") + 1); //Extract base

        return base + "_" + prefix;
    }

    //Return a string of the validity status of the current certificate
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
