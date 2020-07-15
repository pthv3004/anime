package jpa;

import model.anime47.CategoryEntity;
import model.anime47.MovieEntity;
import model.phimmoiz.AnimeEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnimeJPA implements Serializable {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("WibuLover");

    public void persistMovie(MovieEntity movieEntity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movieEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught");
        } finally {
            em.close();
        }
    }

    public List<CategoryEntity> getCategories() {
        EntityManager em = emf.createEntityManager();
        List<CategoryEntity> categories = null;
        try {

            String jpql = "Select c from CategoryEntity c";
            Query query = em.createQuery(jpql);

            categories = (List<CategoryEntity>) query.getResultList();

        } catch (Exception e) {

        } finally {
            em.close();
        }
        return categories;
    }

    public List<MovieEntity> getMovies() {
        EntityManager em = emf.createEntityManager();
        List<MovieEntity> movieEntities = null;
        try {
            String jpql = "Select m from MovieEntity m order by m.viewNum desc";
            Query query = em.createQuery(jpql);
            movieEntities = query.setMaxResults(30).getResultList();
        } finally {
            em.close();
        }
        return movieEntities;
    }

    public List<String> getImageLink() {
        EntityManager em = emf.createEntityManager();
        List<String> imageLinks = null;
        try {
            String jpql = "Select m.image from MovieEntity m where m.movieId > 179998";
            Query query = em.createQuery(jpql);
            imageLinks = query.getResultList();
        } finally {
            em.close();
        }
        return imageLinks;
    }

    public MovieEntity getMovieById(int movieId) {
        EntityManager em = emf.createEntityManager();
        MovieEntity movieEntity = null;
        try {
            String jpql = "Select m from MovieEntity m " +
                    "where m.movieId = :movieId";
            Query query = em.createQuery(jpql);
            query.setParameter("movieId", movieId);
            movieEntity = (MovieEntity) query.getSingleResult();
        } finally {
            em.close();
        }
        return movieEntity;
    }

    public List<MovieEntity> searchByLikeName(String searchValue) {
        EntityManager em = emf.createEntityManager();
        List<MovieEntity> movieEntities = new ArrayList<>();
        try {
            String jpql = "SELECT m from MovieEntity m "
                    + "where m.movieName LIKE :searchValue "
                    + "order by m.movieName ";
            Query query = em.createQuery(jpql);
            query.setParameter("searchValue", "%" + searchValue + "%");
            movieEntities = query.setMaxResults(30).getResultList();
        } finally {
            em.close();
        }
        return movieEntities;
    }

    public List<MovieEntity> searchByLikeSeason(String searchValue) {
        EntityManager em = emf.createEntityManager();
        List<MovieEntity> movieEntities = null;
        try {
            String jpql = "SELECT m from MovieEntity m "
                    + "where m.season LIKE :searchValue "
                    + "order by m.viewNum desc ";
            Query query = em.createQuery(jpql);
            query.setParameter("searchValue", "%" + searchValue + "%");
            movieEntities = query.setMaxResults(15).getResultList();
        } finally {
            em.close();
        }
        return movieEntities;
    }

    public int getCateIdByCateName(String cateName) {
        EntityManager em = emf.createEntityManager();
        int listCateId = 0;
        try {
            String jpql = "Select c.cateId from CategoryEntity c " +
                    "where c.categoryName = :cateName";
            Query query = em.createQuery(jpql);
            query.setParameter("cateName", cateName);
            listCateId = (int) query.getSingleResult();
        } finally {
            em.close();
        }
        return listCateId;
    }

    public List<Integer> getListMovieIdByCateId(int cateId) {
        EntityManager em = emf.createEntityManager();
        List<Integer> listMovieId = null;
        try {
            String jpql = "Select md.movieEntity.movieId from MovieDetailedEntity md " +
                    "where md.categoryEntity.cateId = :cateId";
            Query query = em.createQuery(jpql);
            query.setParameter("cateId", cateId);
            listMovieId = query.setMaxResults(6).getResultList();
        } finally {
            em.close();
        }
        return listMovieId;
    }

    public List<MovieEntity> getListStatistic() {
        EntityManager em = emf.createEntityManager();
        List<MovieEntity> statisticDTOList = new ArrayList<>();
        try {
            String jpql = "select c.categoryName, m.season, sum(m.viewNum) " +
                    "from MovieDetailedEntity md " +
                    "inner join CategoryEntity c on c.cateId = md.categoryEntity.cateId " +
                    "inner join MovieEntity m on m.movieId = md.movieEntity.movieId " +
                    "group by c.categoryName, m.season " +
                    "order by sum(m.viewNum)desc ";
            Query query = em.createQuery(jpql);
            statisticDTOList = query.setMaxResults(30).getResultList();
        } finally {
            em.close();
        }
        return statisticDTOList;
    }

    public void persistAnime(AnimeEntity anime) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(anime);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught");
        } finally {
            em.close();
        }
    }
//    public List<CategoryEntity> getCategoriesByMovieId(String )
}
