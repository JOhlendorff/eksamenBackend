/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.google.gson.stream.JsonReader;
import entities.Project;
import entities.ProjectHours;
import entities.User;
import facades.ProjectFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jenso
 */
public class Populator {
    
    static EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    ProjectFacade facade = ProjectFacade.getProjectFacade(emf);

    public static void populate(){

        EntityManager em = emf.createEntityManager();
        User user1 = new User("Jens", "Password123");
        List<User> assignedDevelopers = new ArrayList();
        assignedDevelopers.add(user1);
        Project project1 = new Project("Example name", "Example description", assignedDevelopers);
        ProjectHours projectHours1 = new ProjectHours(3, "I want that", user1);
        //project1.addProjecthours(projectHours1);
        try{
            em.getTransaction().begin();
            em.persist(projectHours1);
            
            em.persist(project1);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
        


    /* public static void populateWithEquipment() {
    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
        Player player1 = new Player("Nikolaj", "Hamster16");
        Player DM = new Player("Cathrine", "Portraet11");
        Player player2 = new Player("Jens", "Skeletor69");
        AbillityScores abiSco1 = new AbillityScores(18, 8, 14, 12, 14, 10);
        Random randi = new Random(0);
        Skills skils = new Skills(randi.nextInt(5), randi.nextInt(5), randi.nextInt(5), randi.nextInt(5), randi.nextInt(5), randi.nextInt(5), randi.nextInt(5), randi.nextInt(5), randi.nextInt(5), randi.nextInt(5), randi.nextInt(5), randi.nextInt(5), randi.nextInt(5), randi.nextInt(5), randi.nextInt(5), randi.nextInt(5), randi.nextInt(5));
       Character ch1 = new Character(5, 104, 85, 17, 30, "Damascus", "He was a valiant paladin.", "orc", "paladin", abiSco1, skils);
        Equipment equipment = new Equipment("Helm Of Glory", 1.5);
        ch1.addInventory(new Inventory(equipment, 1));
        equipment.addInventory(ch1.getInventories().get(0));
        try {
            em.getTransaction().begin();
            Role playerRole = new Role("player");
            Role DMRole = new Role("dungeonmaster");
            player1.addRole(playerRole);
            DM.addRole(DMRole);
            player2.addRole(playerRole);
            //both.addRole(DMRole); // MAN KAN GODT HAVE BEGGE ROLLER CATHRINE !!!
            em.persist(playerRole);
            em.persist(DMRole);
//            em.persist(equipment);
            em.persist(player1);
            player1.addCharacter(ch1);
            //em.persist(ch1);
            em.merge(player1);
            em.persist(DM);
            em.persist(player2);
            em.getTransaction().commit();
            System.out.println("");
        } finally {
            em.close();
        }
    }*/
    public static void main(String[] args) throws Exception {
        // populateWithEquipment();
        populate();

    }
}
    /*
    public static void test() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager emDelete = emf.createEntityManager();
        try {
            emDelete.getTransaction().begin();
            emDelete.createQuery("DELETE FROM Character").executeUpdate();
            emDelete.createQuery("DELETE FROM Player").executeUpdate();
            emDelete.createQuery("DELETE FROM Equipment").executeUpdate();
            emDelete.createQuery("DELETE FROM Role").executeUpdate();
            emDelete.getTransaction().commit();
        } finally {
            emDelete.close();
        }
        EntityManager em = emf.createEntityManager();
        Player player1 = new Player("Nikolaj", "Hamster16");
        //Player DM = new Player("Cathrine", "Portraet11");
        //Player player2 = new Player("Jens", "Skeletor69");
        AbillityScores abiSco1 = new AbillityScores(18, 8, 14, 12, 14, 10);
        Character ch1 = new Character(5, 104, 85, 17, 30, "Damascus", "He was a valiant paladin.", "orc", "paladin", abiSco1);
        //Equipment equipment = new Equipment("Helm Of Glory", 1, 1.5);
        try {
            em.getTransaction().begin();
            //Role playerRole = new Role("player");
            //   Role DMRole = new Role("dungeonmaster");
           // player1.addRole(playerRole);
            //   DM.addRole(DMRole);
            //   player2.addRole(playerRole);
            //both.addRole(DMRole); // MAN KAN GODT HAVE BEGGE ROLLER CATHRINE !!!
           // em.persist(playerRole);
            //  em.persist(DMRole);
            //  em.persist(equipment);
            player1.addCharacter(ch1);
            em.persist(ch1);
            em.persist(player1);
            // em.persist(DM);
            // em.persist(player2);
            em.getTransaction().commit();
            System.out.println("");
        } finally {
            em.close();
        }
     */
    
