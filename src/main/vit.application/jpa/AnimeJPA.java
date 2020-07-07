package jpa;

import model.ActorEntity;
import model.AnimeEntity;
import model.GenresEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnimeJPA implements Serializable {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");

    public void persistAnime(AnimeEntity animeEntity){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(animeEntity);
            em.getTransaction().commit();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught");
        }finally {
            em.close();
        }
    }
    public Collection<AnimeEntity> getAllAnime(){

        return null;
    }
    public void persistGenres(GenresEntity genresEntity){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(genresEntity);
            em.getTransaction().commit();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught");
        }finally {
            em.close();
        }
    }
    public void persistActor(ActorEntity actorEntity){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(actorEntity);
            em.getTransaction().commit();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught");
        }finally {
            em.close();
        }
    }
}
