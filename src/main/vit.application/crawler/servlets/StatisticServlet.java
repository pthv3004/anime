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
import java.util.List;

@WebServlet(name = "StatisticServlet", urlPatterns = "/StatisticServlet")
public class StatisticServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String season = req.getParameter("season");
        System.out.println(season);
        AnimeJPA animeJPA = new AnimeJPA();
        try {
            List<MovieEntity> movieEntities = animeJPA.searchByLikeSeason(season);
            HttpSession session = req.getSession();
            session.setAttribute("MOVIES",movieEntities);
        }finally {
            req.getRequestDispatcher("statisticPage.jsp").forward(req,resp);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
