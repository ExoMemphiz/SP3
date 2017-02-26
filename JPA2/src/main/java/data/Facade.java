package data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Chris
 */
public class Facade {
 
    String puName = "pu";
    
    EntityManager manager;
    EntityTransaction transaction;
    
    public static void main(String[] args) {
        Facade facade = new Facade();
        
    }
    
    public EntityManagerFactory getFactory(String puName) {
        return Persistence.createEntityManagerFactory(puName);
    }
 
    public EntityManager getManager() {
        if (manager == null) {
            EntityManagerFactory factory = getFactory(puName);
            manager = factory.createEntityManager();
        }
        return manager;
    }
    
    public EntityTransaction getTransaction() {
        if (transaction == null) {
            transaction = getManager().getTransaction();
        }
        return transaction;
    }

}