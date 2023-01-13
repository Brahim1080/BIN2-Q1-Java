package be.vinci.api;

import be.vinci.services.ClassAnalyzer;
import be.vinci.classes.User;
import jakarta.json.JsonStructure;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

/**
 * Send class data to make class diagrams
 * The class name must be given, and present into the "classes" package
 */
@Path("classes")
public class Classes {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonStructure getClassInfo(@QueryParam("classname") String classname) {

        String path = "be.vinci.classes." + classname;
        Class cls;
        try {
            cls = Class.forName(path);

        } catch (ClassNotFoundException e) {
            throw new WebApplicationException(404);
        }

        ClassAnalyzer analyzer = new ClassAnalyzer(cls);
        return analyzer.getFullInfo();
    }
}
