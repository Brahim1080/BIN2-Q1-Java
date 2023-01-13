package api;

import domaine.Joke;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import services.Jokes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Path("/jokes")
public class JokeRessource {

    @Inject
    private Jokes jokes;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJokes() {

        List<Joke> jokeList = jokes.getAllJokes();

        if (jokeList.isEmpty())
            return "No jokes";

        String jokesSerialized = jokeList.stream().map(joke -> joke.toString()).collect(
                Collectors.joining("\n")
        );
        return jokesSerialized;
    }

    @GET
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    public String createJoke(@DefaultValue("") @QueryParam("category") String category,
                             @DefaultValue("") @QueryParam("title") String title,
                             @DefaultValue("") @QueryParam("content") String content,
                             @DefaultValue("") @QueryParam("author") String author) {

        Joke joke = new Joke(category, title, content, author);

        String res = jokes.addJoke(joke);
        if (res == null) {
            return "Bad request";
        }

        return "Joke added by id : "+res;
    }
}
