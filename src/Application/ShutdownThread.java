//RhysIT.co.za
package Application;

import Backend.DatabaseOperations;

public class ShutdownThread implements Runnable {

    @Override
    public void run() {
        DatabaseOperations.disconnect();
    }
}
