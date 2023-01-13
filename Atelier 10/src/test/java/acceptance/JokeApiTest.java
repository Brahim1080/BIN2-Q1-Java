package acceptance;

import domaine.Joke;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import main.ApiServer;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Catalog;
import services.Jokes;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JokeApiTest {
    private Jokes defaultJokeList;

    private  Joke joke1;
    private  Joke joke2;
    private  Joke joke3;
    @BeforeEach
    void setUp() {

        joke1 = new Joke("aaaa","title2","content1","Brahim");
        joke2 = new Joke("aaaa","title4","content1","Brahim");
        joke3 = new Joke("aaaa","title77","content1","Brahim");

    }
    @AfterEach
    void tearDown() {
        ApiServer.stop();
    }

    private void bootServerWithDepenecies(Joke ... defaultItem){

        defaultJokeList = new Jokes();
        defaultJokeList.initJokeList(defaultItem);

        ApiServer.setApplicationBinder((new AbstractBinder() {
            @Override
            protected void configure() {
                bind(defaultJokeList).to(Jokes.class);
            }
        }));

        ApiServer.start();
    }

    @Test
    void getJokes() {
        bootServerWithDepenecies(joke1,joke2,joke3);
        String response = getRequest("jokes");
        String exceptedResponse = "id: 1 categorie: aaaa title: title2 content: content1 author: Brahim\n" +
                "id: 2 categorie: aaaa title: title4 content: content1 author: Brahim\n" +
                "id: 3 categorie: aaaa title: title77 content: content1 author: Brahim";

        assertEquals(exceptedResponse,response);


    }

    @Test
    void TestNoJokes() {
        bootServerWithDepenecies();
        ApiServer.setApplicationBinder((new AbstractBinder() {
            @Override
            protected void configure() {
                bind(defaultJokeList).to(Jokes.class);
            }
        }));
        String response = getRequest("jokes");
        String exceptedResponse = "No jokes";
        assertEquals(exceptedResponse,response);

    }

    @Test
    void TestCreateJoke() {
        bootServerWithDepenecies();

        String categorie = "Categoriefffff";
        String title = "titleffff";
        String content = "contentfffff";
        String author = "author222";
        Map<String,String> joke = new HashMap<>();
        joke.put("category", categorie);
        joke.put("title", title);
        joke.put("content", content);
        joke.put("author", author);
        String response = getRequest("jokes/add",joke);
        String idOfNewJoke = response.split(": ")[1];

        String allJokesAfterAdding = getRequest("jokes");
        assertAll(
                () ->  assertTrue(response.contains("Joke added by id : ")),
                () ->  assertTrue(allJokesAfterAdding.contains(idOfNewJoke))
        );


    }

    private String getRequest(String path) {
        Client c = ClientBuilder.newClient();

        WebTarget target = c.target("http://localhost:9998/");


        String responseBody = target.path(path)//.queryParam(key, value)
                .request().get(String.class);
        return responseBody;
    }
    private String getRequest(String path, Map<String,String> queryParams) {
        Client c = ClientBuilder.newClient();

        WebTarget target = c.target("http://localhost:9998/");

        for (String key : queryParams.keySet()) {
            target = target.queryParam(key,queryParams.get(key));
        }

        String responseBody = target.path(path)//.queryParam(key, value)
                .request().get(String.class);
        return responseBody;
    }
}
