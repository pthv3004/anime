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

@WebServlet(name = "DispatcherServlet",urlPatterns = "/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AnimeJPA animeJPA = new AnimeJPA();
        List<MovieEntity> movieEntities = new ArrayList<>();
        HttpSession session = req.getSession();
        try {
            movieEntities = animeJPA.getMovies();
            session.setAttribute("MOVIES",movieEntities);
        }finally {
            req.getRequestDispatcher("homepage.jsp").forward(req,resp);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
