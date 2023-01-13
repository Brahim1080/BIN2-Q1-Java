package be.vinci.api;

import be.vinci.services.InstancesAnalyzer;
import jakarta.json.JsonStructure;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import be.vinci.utils.InstanceGraphBuilder;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Send instances graph data to make object diagrams
 * <p>
 * The instances graphs are initialized by a class containing the "initInstanceGraph" method,
 * building the instance graph, and returning it.
 * <p>
 * The "instance builder class name" must be given and present into the "instances" package
 */
@Path("instances")
public class Instances {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonStructure getInstanceGraphInfo(@QueryParam("builderclassname") String builderClassname) {


        try {

            Class builderClass = Class.forName("be.vinci.instances." + builderClassname);

            Object builderObject = builderClass.getConstructor().newInstance();

            for (Method m : builderClass.getDeclaredMethods()) {

                if (m.isAnnotationPresent(InstanceGraphBuilder.class)) {
                    Object instanceGraph = m.invoke(builderObject);
                    InstancesAnalyzer analyzer = new InstancesAnalyzer(instanceGraph);
                    return analyzer.getFullInfo();
                }
            }
            return  null;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ignore) {
            throw new WebApplicationException(404);
        }

    }
}
