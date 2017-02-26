/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Chris
 */
@Entity
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String name, description;
    private int hoursAssigned, hoursUsed;
    
    private Project project;

    public Task() {
        
    }
    
    public Task(Project project, String name, String description, int hoursAssigned) {
        this.project = project;
        this.name = name;
        this.description = description;
        this.hoursAssigned = hoursAssigned;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entity.Task[ id=" + id + " ]";
    }

    public String getDescription() {
        return description;
    }

    public int getHoursAssigned() {
        return hoursAssigned;
    }

    public int getHoursUsed() {
        return hoursUsed;
    }

    public String getName() {
        return name;
    }

    public Project getProject() {
        return project;
    }
    
}
