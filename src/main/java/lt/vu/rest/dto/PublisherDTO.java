package lt.vu.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.vu.entities.Book;
import lt.vu.entities.Publisher;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDTO {


    private Integer id;
    private String name;
    private List<Integer> bookIds = new ArrayList<>();

    public PublisherDTO(Publisher publisher){
        this.id = publisher.getId();
        this.name = publisher.getName();
        for(Book book : publisher.getBooks()){
            bookIds.add(book.getId());
        }
    }
}
