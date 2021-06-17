package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Project;
import errorhandling.ExceptionDTO;
import facades.ProjectFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

@Path("project")
public class ProjectResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final ProjectFacade facade = ProjectFacade.getProjectFacade(EMF);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String greetings() {
        return "Hello";
    }

    @Path("projecthours/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getProjectHours(@PathParam("id") String id) {
        int hours;
        ExceptionDTO eDTO;
        try {
            hours = facade.getInvoice(Integer.valueOf(id));
        } catch (Exception ex) {
            return ex.toString();
        }
        return GSON.toJson(hours);
    }
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllProjects(){
        List<String> projects;
        try{
            projects = facade.getAllprojects();
        }catch (Exception ex) {
            return ex.toString();
        }
        return GSON.toJson(projects);
    }

    @POST
    @Path("createproject")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createProject(String json) {
        Project project = GSON.fromJson(json, Project.class);
        ExceptionDTO eDTO;
        try {
            facade.createProject(project.getName(), project.getDescription());
        } catch (Exception ex) {
            eDTO = new ExceptionDTO(404, ex.getMessage());
            return eDTO.toString();
        }
        return "Persisted successfully";
        
        
    }
    
}