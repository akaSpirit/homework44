package lesson44;

public class Book {
    private int id;
    private String bookName;
    private String authorName;
    private BookState bookState;

    public Book(int id, String bookName, String authorName) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public BookState getBookState() {
        return bookState;
    }

    public void setBookState(BookState newBookState) {
        this.bookState = newBookState;
    }

}
