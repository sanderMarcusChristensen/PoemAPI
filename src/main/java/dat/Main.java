package dat;

import dat.config.HibernateConfig;
import dat.controller.PoemController;
import dat.daos.PoemDAO;
import dat.dto.PoemDTO;
import dat.entities.Poem;
import dat.enums.Type;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {



        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("poems");
        Poem p1 = Poem.builder()
                .type(Type.HAIKU)
                .poem("biggie smalls")
                .author("big daddy")
                .title("thug life")
                .build();
        PoemDTO pDTO = new PoemDTO(p1);
        PoemDAO dao = new PoemDAO(emf);


        PoemController poemController = new PoemController();

        Javalin app = Javalin.create((config) -> {
            config.router.contextPath = "/poems";
        }).start(7000);

        app.get("/", poemController::getAllPoems);
        app.post("/", poemController::createPoem);
      


    }
}
