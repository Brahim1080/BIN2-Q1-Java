package be.vinci.services;

import jakarta.json.*;

import java.lang.reflect.*;
import java.util.ArrayList;

/**
 * Class analyzer. It saves a class into attribute, from a constructor, and
 * gives a lot of convenient methods to transform this into a JSON object
 * to print the UML diagram.
 */
public class ClassAnalyzer {

    private Class aClass;

    public ClassAnalyzer(Class aClass) {
        this.aClass = aClass;
    }

    /**
     * Create a JSON Object with all the info of the class.
     *
     * @return
     */
    public JsonObject getFullInfo() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name", aClass.getSimpleName());
        objectBuilder.add("fields", getFields());
        objectBuilder.add("methods", getMethods());
        return objectBuilder.build();
    }

    /**
     * Get fields, and create a Json Array with all fields data.
     * Example :
     * [ {}, {} ]
     */
    public JsonArray getFields() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // TODO Add all fields descriptions to array (use the getField() method above)

        for (Field field : aClass.getDeclaredFields()) {

            arrayBuilder.add(getField(field));
        }
        return arrayBuilder.build();
    }

    /**
     * Get a field, and create a Json Object with all field data.
     * Example :
     * {
     * name: "firstname",
     * type: "String",
     * visibility : "private"  // public, private, protected, package
     * isStatic: false,
     * }
     */
    public JsonObject getField(Field f) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        // TODO add missing info

        objectBuilder.add("name", f.getName());
        objectBuilder.add("type", f.getType().getSimpleName());
        objectBuilder.add("visibility", getFieldVisibility(f));
        objectBuilder.add("isStatic", isFieldStatic(f));
        return objectBuilder.build();
    }

    /**
     * Return whether a field is static or not
     *
     * @param f the field to check
     * @return true if the field is static, false else
     */
    private boolean isFieldStatic(Field f) {

        return Modifier.isStatic(f.getModifiers());
    }

    /**
     * Get field visibility in a string form
     *
     * @param f the field to check
     * @return the visibility (public, private, protected, package)
     */
    private String getFieldVisibility(Field f) {
        int mod = f.getModifiers();


        if (Modifier.isPublic(mod))
            return "Public";

        if (Modifier.isPrivate(mod))
            return "Private";

        if (Modifier.isProtected(mod))
            return "Protected";



        return "Autre";
    }

    //method

    public JsonArray getMethods() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // TODO Add all fields descriptions to array (use the getField() method above)

        for (Method method : aClass.getDeclaredMethods()) {

            arrayBuilder.add(getMethod(method));
        }
        return arrayBuilder.build();
    }

    public JsonObject getMethod(Method m) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        // TODO add missing info

        objectBuilder.add("name", m.getName());
        objectBuilder.add("returnType", m.getReturnType().getSimpleName() );
        objectBuilder.add("parameters ", getTypeParameters(m).toString());
        objectBuilder.add("visibility ", getMethodVisibility(m));
        objectBuilder.add("isStatic", Modifier.isStatic(m.getModifiers()));
        objectBuilder.add("isAbstract",Modifier.isAbstract(m.getModifiers()) );

        return objectBuilder.build();
    }

    private String getMethodVisibility(Method m){
        int mod = m.getModifiers();

        if (Modifier.isPublic(mod))
            return "Public";

        if (Modifier.isPrivate(mod))
            return "Private";

        if (Modifier.isProtected(mod))
            return "Protected";



        return "Autre";

    }
    private JsonArray getTypeParameters(Method m){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Class<?> type : m.getParameterTypes()) {

            arrayBuilder.add(type.getSimpleName());
        }
        return arrayBuilder.build();

    }



}
