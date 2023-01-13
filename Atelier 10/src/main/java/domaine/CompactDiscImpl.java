package domaine;

public class CompactDiscImpl implements CompactDisc {
    private String title;
    private String artist;
    private int stock;
    private double price;
    private String id;

    public CompactDiscImpl(String title, String artist, int stock, double price, String id) {
        this.title = title;
        this.artist = artist;
        this.stock = stock;
        this.price = price;
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public String getArtist() {
        return artist;
    }

    public String toString() {
        return "title:" + title +
                ", artist:" + artist +
                ", stock:" + stock +
                ", price:" + price + "€" +
                ", id:" + id;
    }




}
