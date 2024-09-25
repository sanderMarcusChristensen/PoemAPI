package dat.dto;

import dat.entities.Poem;
import dat.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoemDTO {

    private Long id;
    private Type type;
    private String poem;
    private String author;
    private String title;

    public PoemDTO(Poem poem){
        this.id = poem.getId();
        this.type = poem.getType();
        this.poem = poem.getPoem();
        this.author = poem.getAuthor();
        this.title = poem.getTitle();
    }

}
