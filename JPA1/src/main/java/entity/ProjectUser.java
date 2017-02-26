/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Chris
 */
@Entity
public class ProjectUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String username, email;
    @Temporal(TemporalType.DATE)
    private Date created;
    
    List<Project> projects;

    public ProjectUser() {
    }

    public ProjectUser(String username, String email) {
        this.username = username;
        this.email = email;
        created = new Date();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entity.ProjectUser[ id=" + id + " ]";
    }

    public Date getCreated() {
        return created;
    }

    public String getEmail() {
        return email;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public String getUsername() {
        return username;
    }
    
}
