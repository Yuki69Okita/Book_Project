package uni.book_project;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String bookName;

    private String bookGenre;

    protected Book() {
    }

    public Book(String bookName, String bookGenre) {
        this.bookName = bookName;
        this.bookGenre = bookGenre;
    }

    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    @Override
    public String toString() {
        return String.format("Book[id=%d, bookName='%s', bookGenre='%s']", id,
                bookName, bookGenre);
    }

}