package dat.entities;

import dat.dto.PoemDTO;
import dat.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Poem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Type type;
    @Column(length = 1000)
    private String poem;
    private String author;
    private String title;

    public Poem(PoemDTO poem) {
        this.id = poem.getId();
        this.type = poem.getType();
        this.poem = poem.getPoem();
        this.author = poem.getAuthor();
        this.title = poem.getTitle();
    }
}
