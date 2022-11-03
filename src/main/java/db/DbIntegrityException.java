package db;

public class DbIntegrityException extends RuntimeException {

        //exceção personalisada de integridade referencial
        public DbIntegrityException (String msg){
                super(msg);
        }
}
