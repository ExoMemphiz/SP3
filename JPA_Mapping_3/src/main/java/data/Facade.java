package data;

import entity.Customers;
import entity.Employees;
import entity.Offices;
import entity.Orders;
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
        for (Orders o : facade.getOrdersOnHold(144)) {
            System.out.println(o.getOrderNumber());
        }
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
    
    public Employees createEmployee() {
        
        Query q = getManager().createQuery("SELECT MAX(e.employeeNumber) FROM Employees e");
        
        Object o = q.getSingleResult();
        int empID = ((int) o) + 1;
        System.out.println("empID: " + empID);
        
        
        String officeCode = "1";
        Query q2 = getManager().createQuery("SELECT o FROM Offices o where o.officeCode = :officeNumber");
        q2.setParameter("officeNumber", officeCode);
        
        int employeeID = ((int) q.getSingleResult()) + 1;
        Offices office = (Offices) q2.getSingleResult();
        
        Employees empl = new Employees(employeeID, "TestLastName", "TestFirstName", "x666", "Test@gmail.com", "TestTitle");
        empl.setOffices(office);

        getTransaction().begin();
        getManager().persist(empl);
        getTransaction().commit();
        return empl;
    }
    
    public Customers updateCustomer(Customers customer) {
        //int id = customer.getCustomerNumber();
        getTransaction().begin();
        getManager().persist(customer);
        getTransaction().commit();
        //EntityTransaction 
        return customer;
    }
    
    public void executeQueries(Query... qs) {
        for (Query q : qs) {
            q.executeUpdate();
        }
    }
 
    public long getEmployeeCount() {
        Query q1 = getManager().createQuery("SELECT COUNT(e.employeeNumber) FROM Employees e");
        return (long) q1.getSingleResult();
    }
    
    public List<Customers> getCustomersInCity(String city) {
        Query q1 = getManager().createQuery("SELECT c FROM Customers c WHERE c.city = :city");
        q1.setParameter("city", city);
        return q1.getResultList();
    }
    
    public Employees getEmployeeMaxCustomers() {
        Query q1 = getManager().createQuery("");
        return null;
    }
    
    public List<Orders> getOrdersOnHold() {
        Query q1 = getManager().createQuery("SELECT o FROM Orders o WHERE o.status = :status");
        q1.setParameter("status", "On Hold");
        return q1.getResultList();
    }
    
    public List<Orders> getOrdersOnHold(int customerID) {
        Query q1 = getManager().createQuery("SELECT o FROM Orders o JOIN Customers c ON o.customers.customerNumber = c.customerNumber AND c.customerNumber = :customerID WHERE o.status = :status");
        q1.setParameter("status", "On Hold");
        q1.setParameter("customerID", customerID);
        return q1.getResultList();
    }
    
}