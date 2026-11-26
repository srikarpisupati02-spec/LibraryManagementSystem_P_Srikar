package items;

public class ReferenceBook extends LibraryItem {

    private String author;

    public ReferenceBook(String id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String getType() {
        return "REFERENCE";
    }
}
