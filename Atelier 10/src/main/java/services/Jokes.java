package services;

import domaine.Joke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jokes {
    private List<Joke> jokeList = new ArrayList<>();



    public List<Joke> getAllJokes() {
        return jokeList.stream().toList();
    }

     public String addJoke(Joke joke){

         if (joke.getId() == null || joke.getId().isBlank()) {
             return null;
         }
         if (joke.getTitle() == null || joke.getTitle().isBlank()) {
             return null;
         }
         if (joke.getContent() == null || joke.getContent().isBlank()) {
             return null;
         }
         if (joke.getAuthor() == null || joke.getAuthor().isBlank()) {
             return null;
         }

        this.jokeList.add(joke);

         return String.valueOf(joke.getId());
     }

    public void initJokeList(Joke ... defaultItems){
        if (defaultItems.length == 0){
            jokeList = new ArrayList<>();
        }else{
            jokeList =new ArrayList<>(Arrays.asList(defaultItems)) ;
        }
    }


}
