/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Project;
import entities.ProjectHours;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author jenso
 */
public class ProjectFacade {
    private static ProjectFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private ProjectFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ProjectFacade getProjectFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ProjectFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public int getInvoice(int id){
        EntityManager em = emf.createEntityManager();
        ProjectHours h = em.find(ProjectHours.class, id);
        em.close();
        int invoice = h.getHoursSpent() * 150; //150 kr pr time
        return invoice;
    }
    
    public List<String> getAllprojects(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Project> projectQuery = em.createQuery("SELECT p FROM project p", Project.class);
        List<Project> projectList = projectQuery.getResultList();
        List<String> projectNames = new ArrayList();
        for (Project project : projectList) {
            projectNames.add(project.getName());
        }
        return projectNames;
    }
    
    public void createProject(String name, String description){
        EntityManager em = emf.createEntityManager();
        Project project = new Project();
        project.setDescription(description);
        project.setName(name);
        em.getTransaction().begin();
        em.persist(project);
        em.getTransaction().commit();
        em.close();
    }
    
    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM project r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }

}
