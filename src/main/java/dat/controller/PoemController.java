package dat.controller;

import dat.config.HibernateConfig;
import dat.daos.PoemDAO;
import dat.dto.PoemDTO;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;


public class PoemController {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("poems");
    private final PoemDAO dao = new PoemDAO(emf);

   // GET	/poem	Returns all poems from the database
    public void getAllPoems(Context ctx){
        if (dao.getAllPoems().isEmpty()) {
            ctx.result("No poems found");
            ctx.status(404);
        } else {
            ctx.status(200);
            ctx.json(dao.getAllPoems());
        }
    }

    // POST	/poem	Creates a new poem
    public void createPoem(Context ctx) {
        try {
            // Parse the JSON request body into PoemDTO
            PoemDTO poemDTO = ctx.bodyAsClass(PoemDTO.class);

            // Save the new poem to the database using PoemDAO
            PoemDTO savedPoemDTO = dao.createPoem(poemDTO);

            // Return a success response with the newly created poem
            ctx.status(201); // 201 Created
            ctx.json(savedPoemDTO);

        } catch (Exception e) {
            // Handle any potential errors (e.g., database issues, invalid input)
            ctx.status(500).result("An error occurred while creating the poem");
        }
    }

    // GET	/poem/{id}	Returns a specific poem based on the id
   // PUT	/poem/{id}	Updates an existing poem
   // DELETE	/poem/{id}	Deletes an existing poem

}
