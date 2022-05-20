package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "select a from Book as a")
})
@Table(name = "BOOK")
@Getter @Setter
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "TITLE")
    private String title;

    @ManyToOne
    @JoinColumn(name= "PUBLISHER_ID")
    private Publisher publisher;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @ManyToMany
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id",referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "book_ID",referencedColumnName = "ID"))
    List<Author> bookAuthors = new ArrayList<>();

    public Book() {
    }

    @Override
    public String toString() {
        return getId().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
