package main;

import annotations.GET;
import annotations.POST;
import annotations.Path;
import com.google.common.reflect.ClassPath;

import domain.Request;


import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Server {
    private Request request;

    public Server(Request request) {
        this.request = request;
    }

    public void listenKeyboard() throws InterruptedException {
        System.out.println("Type commands here :");
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        while (true) {
            // Read and split line
            String command = input.nextLine();
            String[] split = command.split(" ");
            // Check line
            if (split.length < 2) {
                System.err.println("Malformed request");
                continue;
            }

            request.setMethod(split[0]);
            request.setURL(split[1]);
            // Delegate request handling

            Thread thread = new Thread(){
                @Override
                public void run() {
                    handleRequest(request);
                }
            };
            thread.start();

            //thread.join();
        }
    }

    public void listenNetwork(int port) {
        // Do not implement !
    }

    /**
     * Cette méthode va exécuter la méthode qui permet de gérer la requête reçue en
     * paramètre. Elle fait appel à "getMethod()" pour récupérer la bonne méthode.
     * <p>
     * S’il n’y a pas de méthode correspondante à la requête, elle affiche
     * “HTTP/1.0 404 Not Found” dans la console.
     * <p>
     * S'il y a un autre souci, elle affiche “HTTP/1.0 500 Internal Server Error”
     * dans la console.
     *
     * @param request la requête à traiter
     */
    private void handleRequest(Request request) {
        try {
            Method m = getMethod(request);
            if (m != null) {
                Class c = m.getDeclaringClass();
                Object o = c.getConstructor().newInstance();
                m.invoke(o);
            } else {
                System.out.println("HTTP/1.0 404 Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("HTTP/1.0 500 Internal Server Error");
        }
    }

    /**
     * Cette méthode parcourt le package "resources" et renvoit un ensemble (Set)
     * avec toutes les classes dedans.
     *
     * @return L'ensemble des classes dans le package "resources"
     */
    private Set<Class> getResourcesClasses() {
        try {
            return ClassPath.from(ClassLoader.getSystemClassLoader())
                    .getAllClasses()
                    .stream()
                    .filter(c -> c.getPackageName().equalsIgnoreCase("resources"))
                    .map(c -> c.load())
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cette méthode parcourt toutes les classes du package resources (utilisez la méthode
     * “getResourcesClasses()” pour cela), parcourt toutes les méthodes de chaque classe,
     * et vérifie si une méthode peut répondre à la requête en paramètre :
     * - la valeur de l'annotation @Path est la même que l'URL dans la requête
     * - une annotation @GET ou @POST est présente, en fonction de la méthode de la
     * requête. Il faut être case-insensitive (Get, GET, get, GeT...)
     * - la méthode doit être public
     * - la méthode ne peut pas être static
     *
     * @param request la requête à traiter
     * @return la méthode correspondante, null si aucune méthode est trouvée.
     */
    private Method getMethod(Request request) {

        for (Class cl : getResourcesClasses()) {

            for (Method method : cl.getMethods()) {
                if (method.isAnnotationPresent(Path.class)) {

                    Path path = method.getAnnotation(Path.class);

                    String pathValue = path.value().split("/")[1];

                    if (pathValue.equalsIgnoreCase(request.getURL())) {

                        if (method.isAnnotationPresent(GET.class)
                                && (request.getMethod().equalsIgnoreCase(GET.class.getSimpleName()))
                                || method.isAnnotationPresent(POST.class)
                                && (request.getMethod().equalsIgnoreCase(POST.class.getSimpleName())
                        )) return method;
                    }

                }
            }

        }
        return null;
    }

}
