//RhysIT.co.za
package Engine;

import Backend.DatabaseOperations;

public class Main {
    public static void main (String[] args){
        DatabaseOperations.connect();
        DatabaseOperations.queryVoid("Create table certificates (id integer, name string");

        DatabaseOperations.disconnect();
    }
}
