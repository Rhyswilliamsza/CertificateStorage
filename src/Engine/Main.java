//RhysIT.co.za
package Engine;

import Backend.DatabaseOperations;

public class Main {
    public static void main(String[] args) {
        DatabaseOperations.connect();


        Interface.AddCertificate test = new Interface.AddCertificate();

        //DatabaseOperations.disconnect();
    }
}
