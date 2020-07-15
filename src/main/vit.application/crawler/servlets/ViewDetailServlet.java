package crawler.servlets;

import jpa.AnimeJPA;
import model.anime47.MovieEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ViewDetailServlet",urlPatterns = "/ViewDetailServlet")
public class ViewDetailServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieId = req.getParameter("movieId");
        AnimeJPA animeJPA = new AnimeJPA();
        try {
            MovieEntity movieEntity = animeJPA.getMovieById(Integer.parseInt(movieId));
            List<MovieEntity> movieEntities = new ArrayList<>();
            MovieEntity guidelineMovie;
            System.out.println(movieEntity.getMovieDetailedEntities().size());
            String categories[] = movieEntity.getCategories().split(",");
            int cateId = animeJPA.getCateIdByCateName(categories[0]);
            List<Integer> listMovieId = animeJPA.getListMovieIdByCateId(cateId);
            for (int idMovie : listMovieId) {
                guidelineMovie = animeJPA.getMovieById(idMovie);
                movieEntities.add(guidelineMovie);
            }
            HttpSession session = req.getSession();
            session.setAttribute("MOVIE",movieEntity);
            session.setAttribute("MOVIES",movieEntities);
        }finally {
            req.getRequestDispatcher("detailpage.jsp").forward(req,resp);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
