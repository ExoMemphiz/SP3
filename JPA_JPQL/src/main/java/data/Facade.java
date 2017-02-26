package data;

import entity.Student;
import entity.Studypoint;
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
        //facade.addStudyPointTest("jan", "3rd Study", 8, 5);
        System.out.println(facade.getTotalStudypoints());
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
    
    public List<Student> findAllStudents() {
        Query q1 = getManager().createNamedQuery("Student.findAll");
        return q1.getResultList();
    }
    
    public Student findStudentByFirstName(String name) {
        Query q1 = getManager().createNamedQuery("Student.findByFirstname");
        q1.setParameter("firstname", name);
        return (Student) q1.getSingleResult();
    }
    
    public Student findStudentByLastName(String name) {
        Query q1 = getManager().createNamedQuery("Student.findByLastname");
        q1.setParameter("lastname", name);
        return (Student) q1.getSingleResult();
    }

    public long getTotalStudypoints(int studentID) {
        Query q1 = getManager().createQuery("SELECT SUM(p.score) FROM Student s, Studypoint p where s.id = p.student.id AND s.id = :studentID");
        q1.setParameter("studentID", studentID);
        return (long) q1.getSingleResult();
    }

    public long getTotalStudypoints() {
        Query q1 = getManager().createQuery("SELECT SUM(p.score) FROM Student s, Studypoint p where s.id = p.student.id");
        return (long) q1.getSingleResult();
    }

    public void createStudent(String firstname, String lastname) {
        Student s = new Student(firstname, lastname);
        getTransaction().begin();
        getManager().persist(s);
        getTransaction().commit();
    }
    
    public void addStudyPointTest(String firstName, String studyPointName, int maxval, int score) {
        Student s = findStudentByFirstName(firstName);
        Studypoint point = new Studypoint(studyPointName, maxval, score, s);
        s.addStudyPoint(point);
        getTransaction().begin();
        getManager().persist(point);
        getManager().persist(s);
        getTransaction().commit();
    }
    
}