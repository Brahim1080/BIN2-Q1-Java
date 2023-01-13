package domaine;

import java.util.UUID;

public class Joke {
    private String id;
    private String categorie;
    private String title;
    private String content;
    private String author;

    public Joke( String categorie, String title, String content, String author) {
        this.id = UUID.randomUUID().toString();
        this.categorie = categorie;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public String getId() {
        return id;
    }



    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return
                "id: " + id + " categorie: " + categorie + " title: " + title + " content: " +
                        content +  " author: " + author;
    }
}
