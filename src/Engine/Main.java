//RhysIT.co.za
package Engine;

import Backend.CertificateOperations;
import Backend.DatabaseOperations;

public class Main {
    public static void main(String[] args) {
        DatabaseOperations.connect();
        CertificateOperations.populateCertificates();
        new Interface.Launcher();

        //DatabaseOperations.disconnect();
    }
}
