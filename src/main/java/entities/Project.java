/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author jenso
 */
@Entity
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String name;
    private String description;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "project")
    private List<ProjectHours> projecthours;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<User> assignedDevelopers; 

    public List<ProjectHours> getProjecthours() {
        return projecthours;
    }

    public void addProjecthours(ProjectHours projecthours) {
        this.projecthours.add(projecthours);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getAssignedDevelopers() {
        return assignedDevelopers;
    }

    public void addAssignedDevelopers(User user) {
        this.assignedDevelopers.add(user);
    }
    public void removeAssignedDevelopers(User user){
        this.assignedDevelopers.remove(user);
    }

    public String getDescription() {
        return description;
    }
    
    

    public Project(String name, String description, List<User> assigned) {
        this.name = name;
        this.description = description;
        this.assignedDevelopers = assigned;
    }

    public Project() {
    }

    public String getName() {
        return name;
    }
    

  
    
}
