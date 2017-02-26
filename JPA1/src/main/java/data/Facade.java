package data;

import entity.Project;
import entity.ProjectUser;
import entity.Task;
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

    public void createUser(String username, String email) {
        ProjectUser user = new ProjectUser(username, email);
        getTransaction().begin();
        getManager().persist(user);
        getTransaction().commit();
    }

    public ProjectUser findUser(String username) {
        Query q1 = getManager().createQuery("SELECT u FROM ProjectUser u WHERE u.username = :username");
        q1.setParameter("username", username);
        return (ProjectUser) q1.getSingleResult();
    }
 
    public List<ProjectUser> getUsers() {
        Query q1 = getManager().createQuery("SELECT u FROM ProjectUser u");
        return q1.getResultList();
    }
    
    public void createProject(String name, String description) {
        Project project = new Project(name, description);
        getTransaction().begin();
        getManager().persist(project);
        getTransaction().commit();
    }
    
    public Project findProject(String name) {
        Query q1 = getManager().createQuery("SELECT p FROM Project p WHERE p.name = :name");
        q1.setParameter("name", name);
        return (Project) q1.getSingleResult();
    }
    
    public void assignUserToProject(Project project, ProjectUser user) {
        project.addUser(user);
        getTransaction().begin();
        getManager().persist(project);
        getTransaction().commit();
    }
    
    public void createTaskAndAssignToProject(Project project, String taskName, String taskDescription, int taskHoursAssigned) {
        Task task = new Task(project, taskName, taskDescription, taskHoursAssigned);
        project.addTask(task);
        getTransaction().begin();
        getManager().persist(task);
        getManager().persist(project);
        getTransaction().commit();
    }
    
}