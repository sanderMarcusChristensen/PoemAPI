package dat.daos;

import dat.dto.PoemDTO;
import dat.entities.Poem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PoemDAO {

    private final EntityManagerFactory emf;

    public PoemDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Set<PoemDTO> getAllPoems() {
        try (EntityManager em = emf.createEntityManager()) {
            //Fetch all movies from the database
            List<Poem> poems = em.createQuery("select p FROM Poem p", Poem.class).getResultList();

            //Convert the list of movies to a set of MovieDTOs and collect them in a set
            Set<PoemDTO> poemDTOS = poems.stream()
                    .map(PoemDTO::new)
                    .collect(Collectors.toSet());
            em.close();
            return poemDTOS;
        } catch (PersistenceException e) {
            System.out.println("Error while getting Poem list" + e);
            return null;
        }
    }

    public PoemDTO createPoem (PoemDTO dto){
        try(EntityManager em = emf.createEntityManager()) {
            Poem poem = new Poem(dto);
            em.getTransaction().begin();
            em.persist(poem);
            em.getTransaction().commit();
            return new PoemDTO(poem);
        } catch (PersistenceException e){
            System.out.println("Error while creating Poem" + e);
        }
        return null;
    }

}
