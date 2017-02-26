/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name, description;
    @Temporal(TemporalType.DATE)
    Date created, lastModified;
    
    List<Task> tasks;
    List<ProjectUser> users;

    public Project() {
    }
    
    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        created = new Date();
        lastModified = created;
        tasks = new ArrayList<>();
        users = new ArrayList<>();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entity.Project[ id=" + id + " ]";
    }

    public Date getCreated() {
        return created;
    }

    public String getDescription() {
        return description;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<ProjectUser> getUsers() {
        return users;
    }
    
    public void addUser(ProjectUser user) {
        users.add(user);
    } 
    
    public void addTask(Task task) {
        tasks.add(task);
    }
    
}
