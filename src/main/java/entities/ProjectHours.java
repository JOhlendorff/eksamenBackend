/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author jenso
 */
@Entity
public class ProjectHours implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int hoursSpent;
    private String userStory;
    @ManyToOne
    private Project project;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    public ProjectHours(int hoursSpent, String userStory, User user) {
        this.hoursSpent = hoursSpent;
        this.userStory = userStory;
        this.user = user;
    }

    public ProjectHours() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}
